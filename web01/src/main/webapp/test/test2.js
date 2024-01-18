function test() {
	let li = document.querySelectorAll('.tabs li');
	let div = document.querySelectorAll('.tab-content');;
	for(i = 0; i < li.length; i++){
		li[i].addEventListener('click', () => {
			for(j = 0; j < li.length; j++){
				li[j].classList.remove('current');
				div[j].classList.remove('current');
			}
/*			e.getAttribute('data-tab');*/
			console.log(this);
/*			console.log(document.querySelector('#'+e.getAttribute('data-tab')).classList);*/
			e.className += 'current';
			document.querySelector('#'+e.getAttribute('data-tab')).classList += 'current';
		});
	}
	
	/*$(document).ready(function() {
		$('ul.tabs li').click(function() {
			var tab_id = $(this).attr('data-tab');

			$('ul.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');

			$(this).addClass('current');
			$("#" + tab_id).addClass('current');
		});

	});*/
}
test();