$(document).ready(function() {
	$(".error").hide();

	$("#checkVal").click( function(event){

			

			var userId      = $("#UserId").val();
			/** 사용자 아이디 유효성 체크 **/

			if( chkValId( userId ) ){

				$("#errMsg_UserId").hide();

			}

			else{

				$("#errMsg_UserId").show();

				$("#errMsg_UserId").text("아이디는 오직 문자와 숫자, _ 기호만 입력가능");

				event.preventDefault();

			}
});

	var chkValId = function( id ){

		var patt = new RegExp(/^[a-z0-9_]+$/);

		return patt.test(id);
}	
