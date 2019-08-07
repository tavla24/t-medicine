
$(document).ready(function() {
    $('#ajbutt').click(function () {
        $.ajax({
            type: "POST",
            //url : "/test/test/get",
            url : "<c:url value="/test/test/get" />",

            success : function(response) {
                $('#source').setAttribute("source", response);
            }
        });
    });
});
