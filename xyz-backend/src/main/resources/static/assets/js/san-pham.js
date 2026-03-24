document.getElementById('modalImageFile').addEventListener('change', function(event) {
    const [file] = event.target.files;
    if (file) {
        document.getElementById('imagePreview').src = URL.createObjectURL(file);
        document.getElementById('imagePreviewContainer').style.display = 'block';
    }
});

function openAddModal() {
    document.getElementById('modalTitle').innerText = 'Thêm sản phẩm mới';
    document.getElementById('modalId').value = '';
    document.getElementById('modalName').value = '';
    document.getElementById('modalCategoryId').value = '';
    document.getElementById('modalImageUrl').value = '';
    document.getElementById('modalDescription').value = '';
    document.getElementById('modalIsActive').checked = true;

    document.getElementById('modalImageFile').value = '';
    document.getElementById('imagePreviewContainer').style.display = 'none';

    var modal = new bootstrap.Modal(document.getElementById('sanPhamModal'));
    modal.show();
}

function openEditModal(id, name, categoryId, imageUrl, description, isActive) {
    document.getElementById('modalTitle').innerText = 'Cập nhật thông tin sản phẩm';
    document.getElementById('modalId').value = id;
    document.getElementById('modalName').value = name;
    document.getElementById('modalCategoryId').value = categoryId;
    document.getElementById('modalImageUrl').value = imageUrl || '';
    document.getElementById('modalDescription').value = description || '';
    document.getElementById('modalIsActive').checked = (isActive === true || isActive === 'true');

    document.getElementById('modalImageFile').value = '';

    if (imageUrl) {
        document.getElementById('imagePreview').src = imageUrl;
        document.getElementById('imagePreviewContainer').style.display = 'block';
    } else {
        document.getElementById('imagePreviewContainer').style.display = 'none';
    }

    var modal = new bootstrap.Modal(document.getElementById('sanPhamModal'));
    modal.show();
}

function changePageSize(size) {
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set('size', size);
    urlParams.set('page', '1');
    window.location.href = window.location.pathname + '?' + urlParams.toString();
}