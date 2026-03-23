function openAddModal() {
    document.getElementById('modalTitle').innerText = 'Thêm danh mục mới';
    document.getElementById('modalId').value = '';
    document.getElementById('modalName').value = '';
    document.getElementById('modalDescription').value = '';
    document.getElementById('modalIsActive').checked = true;

    var modal = new bootstrap.Modal(document.getElementById('loaiSanPhamModal'));
    modal.show();
}

function openEditModal(id, name, description, isActive) {
    document.getElementById('modalTitle').innerText = 'Cập nhật danh mục';
    document.getElementById('modalId').value = id;
    document.getElementById('modalName').value = name;
    document.getElementById('modalDescription').value = description;

    // Xử lý giá trị boolean cẩn thận khi truyền từ Thymeleaf sang JS
    document.getElementById('modalIsActive').checked = (isActive === true || isActive === 'true');

    var modal = new bootstrap.Modal(document.getElementById('loaiSanPhamModal'));
    modal.show();
}