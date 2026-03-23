let cart = [];
let pendingOrders = []; // Mảng lưu các Hóa đơn chờ
let currentTotal = 0;

let sizeModalObj;
let checkoutModalObj;

document.addEventListener("DOMContentLoaded", function () {
    sizeModalObj = new bootstrap.Modal(document.getElementById('sizeModal'));
    checkoutModalObj = new bootstrap.Modal(document.getElementById('checkoutModal'));
});

// 1. API Lấy Size
function openSizeModal(productId, productName) {
    document.getElementById('modalProductName').innerText = productName;

    fetch(`/ban-hang/api/kich-co?productId=${productId}`)
        .then(res => res.json())
        .then(sizes => {
            let html = '';
            sizes.forEach(s => {
                html += `
                <button class="btn btn-outline-primary text-start d-flex justify-content-between align-items-center border-2 rounded-3"
                        onclick="addToCart('${s.id}', '${productName}', '${s.sizeName}', ${s.price})">
                    <span class="fw-bold">Size ${s.sizeName}</span>
                    <span class="fw-bold text-danger">${s.price.toLocaleString('vi-VN')}đ</span>
                </button>`;
            });
            document.getElementById('sizeOptions').innerHTML = html;
            sizeModalObj.show();
        });
}

// 2. Logic Giỏ Hàng
function addToCart(sizeId, name, size, price) {
    let item = cart.find(i => i.productSizeId === sizeId);
    if (item) item.quantity += 1;
    else cart.push({productSizeId: sizeId, name: name, size: size, price: price, quantity: 1});
    sizeModalObj.hide();
    renderCart();
}

function updateQuantity(sizeId, change) {
    let item = cart.find(i => i.productSizeId === sizeId);
    if (item) {
        item.quantity += change;
        if (item.quantity <= 0) cart = cart.filter(i => i.productSizeId !== sizeId);
    }
    renderCart();
}

function clearCart() {
    if (cart.length > 0 && confirm("Xóa toàn bộ giỏ hàng?")) {
        cart = [];
        renderCart();
    }
}

function renderCart() {
    let container = document.getElementById('cartContainer');
    if (cart.length === 0) {
        container.innerHTML = `<tr><td colspan="3" class="text-center text-muted py-5"><i class='bx bx-cart fs-1 opacity-50 mb-2'></i><br>Chưa chọn món</td></tr>`;
        document.getElementById('totalAmount').innerText = '0đ';
        currentTotal = 0;
        return;
    }

    let html = '';
    currentTotal = 0;
    cart.forEach(item => {
        let amount = item.price * item.quantity;
        currentTotal += amount;
        html += `
        <tr>
            <td class="ps-3">
                <div class="fw-bold text-dark" style="font-size: 0.9rem;">${item.name}</div>
                <small class="text-muted">Size ${item.size} - ${item.price.toLocaleString('vi-VN')}đ</small>
            </td>
            <td class="text-center">
                <div class="input-group input-group-sm d-flex justify-content-center">
                    <button class="btn btn-outline-secondary px-2" onclick="updateQuantity('${item.productSizeId}', -1)">-</button>
                    <input type="text" class="form-control text-center px-0 bg-white" style="max-width: 35px;" value="${item.quantity}" readonly>
                    <button class="btn btn-outline-secondary px-2" onclick="updateQuantity('${item.productSizeId}', 1)">+</button>
                </div>
            </td>
            <td class="text-end pe-3 fw-bold text-primary">${amount.toLocaleString('vi-VN')}đ</td>
        </tr>`;
    });
    container.innerHTML = html;
    document.getElementById('totalAmount').innerText = currentTotal.toLocaleString('vi-VN') + 'đ';
}

// 3. Logic Hóa Đơn Chờ
function holdCurrentOrder() {
    if (cart.length === 0) return alert("Giỏ hàng đang trống!");

    let orderName = "HĐ #" + (pendingOrders.length + 1) + " - " + new Date().toLocaleTimeString('vi-VN', {
        hour: '2-digit',
        minute: '2-digit'
    });
    pendingOrders.push({id: Date.now(), name: orderName, total: currentTotal, items: [...cart]});

    cart = [];
    renderCart();
    renderPendingOrders();
}

function openPendingOrder(orderId) {
    let index = pendingOrders.findIndex(o => o.id === orderId);
    if (index > -1) {
        // Nếu giỏ hàng hiện tại đang có đồ, thì lưu nó thành HĐ chờ mới trước khi mở HĐ cũ
        if (cart.length > 0) holdCurrentOrder();

        cart = pendingOrders[index].items; // Lấy data ra
        pendingOrders.splice(index, 1); // Xóa khỏi danh sách chờ
        renderCart();
        renderPendingOrders();
    }
}

function renderPendingOrders() {
    let container = document.getElementById('pendingOrdersContainer');
    let msg = document.getElementById('emptyPendingMsg');
    let badge = document.getElementById('pendingCount');

    badge.innerText = pendingOrders.length;

    if (pendingOrders.length === 0) {
        container.innerHTML = '';
        container.appendChild(msg);
        msg.style.display = 'block';
        return;
    }

    msg.style.display = 'none';
    let html = '';
    pendingOrders.forEach(o => {
        html += `
        <div class="d-flex justify-content-between align-items-center p-2 mb-2 bg-light border rounded-3">
            <div>
                <div class="fw-bold text-dark" style="font-size: 0.85rem;">${o.name}</div>
                <div class="text-danger fw-bold" style="font-size: 0.85rem;">${o.total.toLocaleString('vi-VN')}đ</div>
            </div>
            <button class="btn btn-sm btn-primary rounded-pill px-3" onclick="openPendingOrder(${o.id})">Mở</button>
        </div>`;
    });
    container.innerHTML = html;
}

// 4. Logic Modal Thanh Toán
function openCheckoutModal() {
    if (cart.length === 0) return alert("Vui lòng chọn món để thanh toán!");
    document.getElementById('checkoutTotalLabel').innerText = currentTotal.toLocaleString('vi-VN') + 'đ';
    document.getElementById('customerGiven').value = '';
    document.getElementById('customerChange').innerText = '0đ';
    document.getElementById('payCash').checked = true;
    togglePaymentUI();
    checkoutModalObj.show();
}

function togglePaymentUI() {
    let method = document.querySelector('input[name="paymentMethod"]:checked').value;
    if (method === 'CASH') {
        document.getElementById('cashArea').style.display = 'block';
        document.getElementById('qrArea').style.display = 'none';
    } else {
        document.getElementById('cashArea').style.display = 'none';
        document.getElementById('qrArea').style.display = 'block';
    }
}

function calculateChange() {
    let given = parseInt(document.getElementById('customerGiven').value) || 0;
    let change = given - currentTotal;
    document.getElementById('customerChange').innerText = (change > 0 ? change.toLocaleString('vi-VN') : 0) + 'đ';
    if (change < 0) document.getElementById('customerChange').classList.replace('text-primary', 'text-danger');
    else document.getElementById('customerChange').classList.replace('text-danger', 'text-primary');
}

// 5. Submit xuống Backend
function submitCheckout() {
    let method = document.querySelector('input[name="paymentMethod"]:checked').value;

    if (method === 'CASH') {
        let given = parseInt(document.getElementById('customerGiven').value) || 0;
        if (given < currentTotal) return alert("Số tiền khách đưa chưa đủ!");
    }

    let requestBody = {
        customerId: null,
        totalAmount: currentTotal,
        paymentMethod: method,
        note: "",
        chiTietList: cart.map(item => ({
            productSizeId: item.productSizeId,
            quantity: item.quantity,
            price: item.price
        }))
    };

    fetch('/ban-hang/api/thanh-toan', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(requestBody)
    })
        .then(res => {
            if (res.ok) {
                alert("Thanh toán thành công!");
                cart = [];
                renderCart();
                checkoutModalObj.hide();
            } else {
                res.text().then(text => alert(text));
            }
        })
        .catch(err => console.error("Lỗi:", err));
}

function filterProducts() {
    let selectedCategory = document.getElementById('categoryFilter').value;
    let products = document.querySelectorAll('.product-item');

    products.forEach(item => {
        let itemCategory = item.getAttribute('data-category');
        if (selectedCategory === 'ALL' || itemCategory === selectedCategory) {
            item.style.display = 'block'; // Hiện món
        } else {
            item.style.display = 'none'; // Ẩn món
        }
    });
}