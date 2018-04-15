(function(){
	document.addEventListener("DOMContentLoaded", function(event) { 

		// Navigation Bar
		function check_position(){
  			var body = document.getElementsByTagName("BODY")[0];
  			var nav = document.getElementById("menu");
  			if (window.scrollY > 200){
  				body.classList.add("floating-nav");
  				nav.style="top:0";
  			}else{
				body.classList.remove("floating-nav");
				nav.style="top:-50px";
  			}
		}

		window.onscroll = function(){
			check_position();
		};
		check_position();

    // Push to bottom
    function check_height(){
      var main = document.getElementById("main-body");
      if (main == null){return;}

      var footer = document.getElementById("footer");
      if (footer == null){return;}

      var delta = window.innerHeight - main.clientHeight - footer.clientHeight - 2;
      if (delta > 0){
        main.style = "min-height:" + (delta + main.clientHeight) + "px";
      }
    }

    window.onresize = function(){
      check_height();
    }
    check_height();
	});
})();