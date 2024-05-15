// 場次資訊

$(".confirm").each( function(){
	$(this).wrap('<div class="overlay"></div>')
});

$(".showtime_btn").on('click', function(e){
	e.preventDefault();
	e.stopImmediatePropagation;
	
	var $this = $(this),
			modal = $($this).data("modal");
	
	$(modal).parents(".overlay").addClass("open");
	setTimeout( function(){
		$(modal).addClass("open");
	}, 350);
	
	$(document).on('click', function(e){
		var target = $(e.target);
		
		if ($(target).hasClass("overlay")){
			$(target).find(".confirm").each( function(){
				$(this).removeClass("open");
			});
			setTimeout( function(){
				$(target).removeClass("open");
			}, 350);
		}
		
	});
	
});

$(".no").on('click', function(e){
	e.preventDefault();
	e.stopImmediatePropagation;
	
	var $this = $(this),
			modal = $($this).data("modal");
	
	$(modal).removeClass("open");
	setTimeout( function(){	
		$(modal).parents(".overlay").removeClass("open");
	}, 350);
	
});	




// 電影PV
$(document).ready(function () {
    // 隱藏所有的 tab 內容
    $(".tab_content").hide();
    $(".tab_content:first").show();
   
    // 如果是標籤模式
    $("ul.tabs li a").click(function(e) {
        e.preventDefault(); // 阻止默認跳轉行為

        // 隱藏所有的 tab 內容
        $(".tab_content").hide();
        
        // 獲取所點擊標籤對應的內容
        var activeTab = $(this).attr("href"); 
        $(activeTab).fadeIn(); // 顯示所點擊標籤對應的內容
        
        // 移除所有標籤的 active 樣式，並為所點擊的標籤添加 active 樣式
        $("ul.tabs li a").removeClass("active");
        $(this).addClass("active");
    });
});
