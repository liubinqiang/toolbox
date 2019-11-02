<html>
<head>
    <#include "../header.ftl">
</head>
<body>
<#include "../link.ftl">
<div>
    <h4>服务器信息</h4>
    <table class="table table-bordered">
        <thead>
        <tr>
            <td>操作系统</td>
            <td>CPU信息</td>
            <td>内存信息</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${serverInfo.systemInfo}</td>
            <td>${serverInfo.cpuInfo}</td>
            <td>${serverInfo.memInfo}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>

