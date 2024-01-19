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

function delete_Tag() {
	$(document).ready(function() {
		$('ul.tabs li span:nth-child(2)').click(function() {
			var tab_id2 = $(this).parent().attr('data-tab');
			console.log($(this).parent().next());
			if($(this).parent().next().length == 0 ){
				$(this).parent().prev().addClass('current');
				$("#" + tab_id2).prev().addClass('current');
				$(this).parent().prev().children('span:eq(1)').removeClass('dis-none');
			} else {
				$(this).parent().next().addClass('current');
				$("#" + tab_id2).next().addClass('current');
				$(this).parent().next().children('span:eq(1)').removeClass('dis-none');
			}
			
			$(this).parent().remove();
			$("#" + tab_id2).remove();
		});
	});
}

function all_change() {
	$('ul.tabs li').removeClass('current');
	$('.tab-content').removeClass('current');
	$('ul.tabs li span:nth-child(2)').addClass('dis-none');
}

function create_Tag(uri,title) {
	console.log('uri:'+uri);
	console.log('title:'+title);
	let ulArea = document.querySelector('.tabs');
	let new_li = document.createElement('li');
	let ulinhtml = '<span>' + title + '</span> <span>X</span>'

	let tab = 'tab-';

	let divArea = document.querySelector('.container');
	let new_div = document.createElement('div');
	let divinhtml = '<c:import url="' + uri + '"></c:import>'
	all_change();
	console.log('ulinhtml:'+ulinhtml);
	console.log('divinhtml:'+divinhtml);
	new_li.setAttribute('class', 'tab-link current');
	new_li.setAttribute('data-tab', tab + liTagCount);
	new_li.innerHTML = ulinhtml;

	new_div.setAttribute('id', tab + liTagCount);
	new_div.setAttribute('class', 'tab-content current');
	new_div.innerHTML = divinhtml;

	ulArea.appendChild(new_li);
	divArea.appendChild(new_div);
	new_li.classList.add('current');
	new_div.classList.add('current');

	$('#'+tab+liTagCount).load(uri)
	
	liTagCount++;
	tab_change();
	delete_Tag();
	
}

tab_change();
delete_Tag();
