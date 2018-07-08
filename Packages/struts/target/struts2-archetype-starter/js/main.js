window.onload = function() {
    let name = $('#login_name')[0].value;
    let birthday = $('#login_birthday')[0].value;
    let form =
        '<div class="form-group row">\n' +
        '    <label class="col-form-label col-4 col-sm-3 col-md-2 col-xl-1">Name</label>\n' +
        '    <input class="form-control col-8 col-sm-9 col-md-10 col-xl-11" name="name"/>\n' +
        '</div>\n' +
        '<div class="form-group row">\n' +
        '    <label class="col-form-label col-4 col-sm-3 col-md-2 col-xl-1">Birthday</label>\n' +
        '    <input class="form-control col-8 col-sm-9 col-md-10 col-xl-11" name="birthday"/>\n' +
        '</div>\n' +
        '<div class="form-group row">\n' +
        '    <label class="col-form-label col-4 col-sm-3 col-md-2 col-xl-1">Password</label>\n' +
        '    <input class="form-control col-8 col-sm-9 col-md-10 col-xl-11" name="password" type="password"/>\n' +
        '</div>\n' +
        '<div class="form-group row">\n' +
        '    <label class="col-form-label col-4 col-sm-3 col-md-2 col-xl-1">Please</label>\n' +
        '    <button type="submit" class="btn btn-primary">Sign in</button>\n' + '&nbsp;&nbsp;' +
        '    <button type="submit" class="btn btn-primary">Sign up</button>\n' +
        '</div>\n';
    let reminder = '';
    let errors = $('.errorMessage').map(function() { return $(this).html(); });
    for (let error of errors) {
        reminder += '<div class="alert alert-danger" role="alert">' + error + '</div>\n';
    }
    $('#login').html(form + reminder);
    $('form div input')[0].value = name;
    $('form div input')[1].value = birthday;
    if (reminder != '') {
        console.error(reminder);
    }
};
