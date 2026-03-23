function openAddModal() {
    document.getElementById('modalTitle').innerText = 'Thêm cấu hình giá mới';
    document.getElementById('modalId').value = '';
    document.getElementById('modalProductId').value = '';
    document.getElementById('modalSizeName').value = '';
    document.getElementById('modalPrice').value = '0';
    document.getElementById('modalIsActive').checked = true;
    var modal = new bootstrap.Modal(document.getElementById('kichCoModal'));
    modal.show();
}

function openEditModal(id, productId, size, price, active) {
    document.getElementById('modalTitle').innerText = 'Cập nhật giá bán';
    document.getElementById('modalId').value = id;
    document.getElementById('modalProductId').value = productId;
    document.getElementById('modalSizeName').value = size;
    document.getElementById('modalPrice').value = price;
    document.getElementById('modalIsActive').checked = (active === true || active === 'true');
    var modal = new bootstrap.Modal(document.getElementById('kichCoModal'));
    modal.show();
}

// JS đổi size phân trang đồng bộ với trang sản phẩm
function changePageSize(size) {
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set('size', size);
    urlParams.set('page', '1');
    window.location.href = window.location.pathname + '?' + urlParams.toString();
}