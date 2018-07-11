var editRoleList = new Array();

function postSuccess(result) {
	$('.bindingResult').empty();
	data = JSON.parse(result);
	var text = '';
	if (data instanceof Array) {
		for ( var i in data) {
			text += '<li>' + data[i].field + ': ' + data[i].message + '</li>';
		}
	} else {
		text += '<li>' + data.field + ': ' + data.message + '</li>';
	}
	$('.bindingResult').html(text);
	$('.responseBtn').click(function() {
		location.reload();
	});
	$('#server-response').modal();
}

function postCallback(result) {
	$('.bindingResult').empty();
	data = JSON.parse(result.responseText);
	var text = '';
	if (data instanceof Array) {
		for ( var i in data) {
			text += '<li>' + data[i].field + ': ' + data[i].message + '</li>';
		}
	} else {
		text += '<li>' + data.field + ': ' + data.message + '</li>';
	}
	$('.bindingResult').html(text);
	$('.responseBtn').click(function() {
		$('#server-response').modal('hide');
	});
	$('#server-response').modal();
}

function validateForm() {
	var email = $('#createEmail').text;
	var name = $('#createName').val();
	var password = $('#createPassword').val();
	var role = false;
	document.forms["createForm"]["roles"].each(function() {
		if ($(this).attr("checked") === true) {
			role = true;
		}
	})
	if (email == null || email == "", name == null || name == "",
			password == null || password == "", !role) {
		return false;
	}
	return true;
}

$(document).ready(function() {
	/*
	 * $('#dataTables-example').DataTable({ responsive : false });
	 */
	$('#editrolelist label li p').each(function(index) {
		editRoleList.push($(this).text());
	});
});
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
			if (this.name === "roles") {
				o[this.name] = [ o[this.name] ];
			}
		}
	});
	return o;
};

$(".editbtn").click(function() {
	$('#editrolelist label li input').prop("checked", false);
	$("#true").removeAttr("selected");
	$("#false").removeAttr("selected");
	$("#editForm").modal();
	var id = $(this).closest("td").siblings("td#id").html();
	$.ajax({
		url : "http://localhost:5000/rest/user/" + id,
		method : 'GET',
		async : true,
		success : function(result) {
			$("#editName").val(result.name);
			$("#editId").val(result.id);
			$("#editBalance").val(result.accountBalance);
			$("#editId").attr("value", result.id);
			$("#editEmail").val(result.email);
			if (result.enabled) {
				$("#true").attr("selected", "true");
			} else {
				$("#false").attr("selected", "true");
			}
			var roles = result.roles;
			for (i = 0; i < editRoleList.length; i++) {
				if (roles.indexOf(editRoleList[i]) > -1) {
					$('#' + editRoleList[i] + 'EDIT').prop("checked", true);
				}
			}
		},
		error : function(result) {
		}
	});
});

$('.editsubmitbtn').click(function(event) {
	event.preventDefault();
	var formData = JSON.stringify($('form#edit').serializeObject());
	$.ajax({
		url : "http://localhost:5000/rest/user",
		method : 'PUT',
		contentType : 'application/json; charset=utf-8',
		dataType : 'text',
		data : formData,
		async : true,
		success : function(result) {
			postSuccess(result);
		},
		error : function(result) {
			postCallback(result);
		}
	});
})

$(".delbtn").click(function() {
	$("#delForm").modal();
	var id = $(this).closest("td").siblings("td#id").html();
	$("#delid").val(id);
});

$(".delsubmitbtn").click(function(event) {
	event.preventDefault();
	var id = $("#delid").val();
	$.ajax({
		url : "http://localhost:5000/rest/user/" + id,
		method : 'DELETE',
		async : true,
		dataType : 'text',
		success : function(result) {
			postSuccess(result);
		},
		error : function(result) {
			postCallback(result);
		}
	});
});

$(".createbtn").click(function() {
	$("#createModal").modal();
});
$(".createsubmitbtn").click(function(event) {
	event.preventDefault();
	var formData = JSON.stringify($('form#create').serializeObject());
	$.ajax({
		url : "http://localhost:5000/rest/user",
		method : 'POST',
		contentType : 'application/json; charset=utf-8',
		dataType : 'text',
		data : formData,
		async : true,
		success : function(result) {
			postSuccess(result);
		},
		error : function(result) {
			postCallback(result);
		}
	});

});

$('.showticketbtn').click(
		function() {
			var id = $(this).closest("td").siblings("td#id").html();
			$.ajax({
				url : "http://localhost:5000/rest/tickets/user/" + id,
				method : 'GET',
				success : function(result) {
					data = result;
					var text = '';
					for ( var key in data) {
						text += '<tr value="' + data[key]["id"] + '">' + '<td>'
								+ data[key]["journeyDepartDate"] + '</td>'
								+ '<td>' + data[key]["journeyDepartTime"]
								+ '</td>' + '<td>'
								+ data[key]["journeyRouteDepartureName"]
								+ '</td>' + '<td>'
								+ data[key]["journeyRouteDestinationName"]
								+ '</td>' + '<td>' + data[key]["seatId"]
								+ '</td>' + '<td>' + data[key]["journeyBusId"]
								+ '</td>'+'<td>' + data[key]["journeyRoutePrice"]
								+ '</td>' + '</tr>';
					}
					$('.ticketInfo').html(text);
				},
				error : function() {
					console.log("Error");
				}
			});
			$('#showTickets').modal();

		});
