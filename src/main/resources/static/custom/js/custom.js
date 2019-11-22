$('.dropdown .dropdown-item').on('click', function(){
	$(this).closest('.dropdown').find('#dropdownMenuButton').text( $(this).text() );
});

