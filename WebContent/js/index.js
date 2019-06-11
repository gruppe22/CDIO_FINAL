/* Implement ajax call to rest Service */
$(document).ready(function () {
    loadUsers();
});


function createUser() {
    event.preventDefault();
    var data = $('#ingredientform').serializeJSON();
    $.ajax({
        url: 'rest/ingredient',
        method: 'POST',
        contentType: "application/json",
        data: data,
        success: function (data) {
            alert(JSON.stringify(data));
            loadIngredients();
        }
    });
}

function deleteIngredient(id) {
    if (!id && id != 0) {
        id = $('#deleteid').val();
    }
    event.preventDefault();
    $.ajax({
        url: 'rest/ingredient/' + id,
        method: 'DELETE',
        success: function (data) {
            loadIngredients();
        }
    })
}

function loadUsers() {
    $.get('rest/ingredient', function (data, textStatus, req) {
        $("#ingredienttablebody").empty();
        $.each(data, function (i, elt) {
            $('#ingredienttablebody').append(generateIngredientHTML(elt));
        });
    });
}

function generateIngredientHTML(ingredient) {
    return '<tr><td>' + ingredient.id + '</td>' +
        '<td>' + ingredient.name + '</td>' +
        '<td>' + ingredient.amount + '</td>' +
        '<td onclick="deleteIngredient(' + ingredient.id + ')"><button>slet ingrediens</button></td></tr> '
}

function generateHTML(json) {
    var html = '<tr>';
    $.each(json, function (i, elt) {
        html += '<td>' + JSON.stringify(elt) + '</td>';
    });
    return html += '</tr>';
}