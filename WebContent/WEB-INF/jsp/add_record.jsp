<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>New Record</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addRecord.css" />

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  </head>

  <body>
    <div class="addpage">
      <div class="form">
        <form method='POST' action='addR' align="left">

          日期：<br>
          <input class="input" type="text" placeholder="YYYYMMDD" name="tdate">

          <br>
          <br>

          收/支：<br>

          <label class="radio-inline">
            <input type="radio" name="Type" class="margin:0px" value=TRUE checked>收入
          </label>
          <label class="radio-inline">
            <input type="radio" name="Type" class="radioType" value=FALSE style="display: inline-block;" />支出
          </label>

          <br>
          <br>

          <!-- <select name="Category" size="1" disabled>
                    	<option value="-" selected>-</option> -->
          類別：
          <select name="Category" size="1">
            <option value="-">-</option>
            <option value="食">食</option>
            <option value="衣">衣</option>
            <option value="住">住</option>
            <option value="行">行</option>
            <option value="育">育</option>
            <option value="樂">樂</option>
          </select>

          <br>
          <br>

          選擇金流種類： <br>
          <label class="radio-inline">
            <input type="radio" name="AccountType" value=TRUE checked>現金
          </label>
          <label class="radio-inline">
            <input type="radio" name="AccountType" value=FALSE>銀行帳戶
          </label>

          <br>
          <br>

          金額：<br>
          <input class="input" type="text" name="Amount">

          <br>
          <br>

          備註：<br>
          <input class="input" type="text" name="Notes">

          <br>
          <br>

          <input type="submit" class="buttonadd" value="新增">
          <input type="reset" class="buttonadd" value="清除">
          <div style="text-align: center">
            <a class='hre' href='/Accounting/main' style="text-decoration: none; color: #77AAAD;">回首頁</a>
          </div>

        </form>
      </div>
    </div>

  </body>

  </html>