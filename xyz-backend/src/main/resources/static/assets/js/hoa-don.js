function changePageSize(size) {
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set('size', size);
    urlParams.set('page', '1'); // Reset về trang 1
    window.location.href = window.location.pathname + '?' + urlParams.toString();
}

function openDetailModal(hoaDonId) {
    const modal = new bootstrap.Modal(document.getElementById('chiTietHoaDonModal'));
    const shortCode = '#' + hoaDonId.substring(0, 8).toUpperCase();
    document.getElementById('modalHdCode').innerText = shortCode;
    modal.show();
    console.log("Đang mở chi tiết hóa đơn: ", hoaDonId);
}