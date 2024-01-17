let liTagCount = 4;
let hTagCount = 4;

function tab_change() {
	$(document).ready(function() {
		$('ul.tabs li').click(function() {
			var tab_id = $(this).attr('data-tab');

			$('ul.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');
			$('ul.tabs li span:nth-child(2)').addClass('dis-none');

			$(this).addClass('current');
			$("#" + tab_id).addClass('current');
			$(this).children('span:eq(1)').removeClass('dis-none');
		});
	});
}

function create_Tag() {
	let ulArea = document.querySelector('.tabs');
	let new_li = document.createElement('li');
	let ulinhtml = '<span>메뉴' + liTagCount + '</span><span class="dis-none">X</span>'

	let tab = 'tab-';

	let divArea = document.querySelector('.container');
	let new_div = document.createElement('div');
	let divinhtml = '<p>tab' + liTagCount + '입니다.</p>'

	new_li.setAttribute('class', 'tab-link');
	new_li.setAttribute('data-tab', tab + liTagCount);
	new_li.innerHTML = ulinhtml;

	new_div.setAttribute('id', tab + liTagCount);
	new_div.setAttribute('class', 'tab-content');
	new_div.innerHTML = divinhtml;

	ulArea.appendChild(new_li);
	divArea.appendChild(new_div);

	liTagCount++;
	tab_change();
}

tab_change();
