//드롭다운 동작
$('.dropdown .dropdown-item').on('click', function(){
	$(this).closest('.dropdown').find('#dropdownMenuButton').text( $(this).text() );
});


// 엑셀 업로드 모달 초기화
$('#fileUploadModal button').on('click', function(){
	$('.dz-preview').remove();
	$('#excelInputFile').val('');
});
