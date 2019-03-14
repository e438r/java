<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width =device-width, initial-scale=1, maximum-scale=1"/>
    <title></title>
    <meta charset="utf-8"/>
    <!--layui的css-->
    <script type="text/javascript" src="/jquery/jquery-1.9.1.min.js"></script>

</head>
<body>
<button id="QCREDIT_DATA" style="border: 2px black inset">开关1</button>

</body>
<script>
    $(document).ready(function () {
        $('#QCREDIT_DATA').on('click', function () {
            $.post('/send', {id: 1}, function (data) {
                alert(data);
            });
        });
    });

</script>
</html>