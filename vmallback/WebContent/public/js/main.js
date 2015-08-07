function pub_alert(message)
{
	$.jBox.show(
	{
        title: "异常提醒",
        content: message,
        btnOK: 
        {
            onBtnClick: function(jbox) 
            {
                $.jBox.close(jbox);
            }
        }
    });
}