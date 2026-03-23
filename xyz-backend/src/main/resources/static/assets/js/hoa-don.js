function changePageSize(size) {
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set('size', size);
    urlParams.set('page', '1');
    window.location.href = window.location.pathname + '?' + urlParams.toString();
}

let chiTietModalObj;

document.addEventListener("DOMContentLoaded", function () {
    chiTietModalObj = new bootstrap.Modal(document.getElementById('chiTietHoaDonModal'));
});

function openDetailModal(hoaDonId) {
    const shortCode = '#' + hoaDonId.substring(0, 8).toUpperCase();
    document.getElementById('modalHdCode').innerText = shortCode;

    document.getElementById('modalLoading').style.display = 'block';
    document.getElementById('modalContent').style.display = 'none';
    document.getElementById('chiTietTableBody').innerHTML = ''; // Xóa data cũ

    chiTietModalObj.show();

    // Fetch dữ liệu từ API
    fetch(`/hoa-don/api/chi-tiet?id=${hoaDonId}`)
        .then(res => res.json())
        .then(data => {
            let html = '';
            data.forEach(item => {
                html += `
                    <tr class="border-bottom">
                        <td class="py-3">
                            <div class="fw-bold text-dark" style="font-size: 0.95rem;">${item.productName}</div>
                            <small class="text-muted">Size: ${item.sizeName}</small>
                        </td>
                        <td class="text-center py-3 fw-medium">${item.quantity}</td>
                        <td class="text-end py-3 text-secondary">${item.price.toLocaleString('vi-VN')}đ</td>
                        <td class="text-end py-3 pe-3 fw-bold text-primary">${item.totalAmount.toLocaleString('vi-VN')}đ</td>
                    </tr>
                `;
            });

            document.getElementById('chiTietTableBody').innerHTML = html;

            document.getElementById('modalLoading').style.display = 'none';
            document.getElementById('modalContent').style.display = 'block';
        })
        .catch(err => {
            console.error("Lỗi tải chi tiết: ", err);
            document.getElementById('modalLoading').innerHTML = '<p class="text-danger mb-0">Lỗi kết nối máy chủ. Vui lòng thử lại!</p>';
        });
}