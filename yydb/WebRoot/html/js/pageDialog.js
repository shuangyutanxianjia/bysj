(function(a) { a.PageDialog = function(c, q) { var f = { W: 0, H: 0, obj: null, oL: 0, oT: 0, close: true, autoClose: false, autoTime: 1500, title: "", animation: true, ready: function() {}, submit: function() {} }; var d = { obj: null, oL: 0, oT: 0, close: true, autoClose: false, autoTime: 1500, title: "", animation: true, ready: function() {}, submit: function() {} };
		q = q || d;
		a.extend(f, q); var h = getobj("pageDialog"); var l = getobj("pageDialogBG"); var i = getobj("pageDialogMain"); var p = getobj("pageDialogClose"); var b = a(window);
		f.animation = (window._IsIE) ? false : f.animation;
		a.PageDialog.prototype.dialogCurrent = a.PageDialog.prototype.dialogCurrent || 0; var m = function(s, u, r) { var t = function() {
				(u && typeof u === "function") && u() }; if(!f.animation) { t(); return } if(s === "zoomIn" && h.data("dialogCurrent") > 1) { return } if(s === "zoomOut" && r === false) { t(); return } h.removeClass(s + " zoom-animated").addClass(s + " zoom-animated").one("webkitAnimationEnd mozAnimationEnd oanimationend animationend", function() { a(this).removeClass(s + " zoom-animated");
				t() }) }; var o = function() { i.empty().css({ width: "auto", height: "auto" }) }; var e = function(s, r) { b.unbind("resize"); if(r == undefined) { r = true } m("zoomOut", function() { o();
				l.hide();
				h.hide().css("opacity", 0).data("dialogCurrent", 0);
				a.PageDialog.prototype.dialogCurrent = 0 }, !!r); if(typeof(f.submit) == "function") { f.submit() } }; var n = function() { var r = 0,
				s = 0; var t; if(f.obj != null) { if(f.obj.length < 1) { f.obj = null } } if(!f.close) { p.hide() } else { p.show() } if(f.title != "") { p.addClass("pageDialogClose2") } c = '<div class="content">' + c + "</div>"; if(f.title != "") { c = '<div class="title">' + f.title + "</div>" + c } o();
			i.html(c); if(window._IeVersion <= 7) { t = i.find(".mAltFail, .mAltOK"); if(f.W === "auto" && t.length >= 1) { t.css("float", "left") } } h.show().data("dialogCurrent", ++a.PageDialog.prototype.dialogCurrent);
			r = i.outerWidth();
			s = i.outerHeight();
			f.W = (typeof f.W === "number" && f.W) ? ((Math.abs(f.W - r) <= 100) ? Math.max(f.W, r) : f.W) : r;
			f.H = (typeof f.H === "number" && f.H) ? (f.H <= s ? s : f.H) : s;
			i.css({ width: f.W + "px", height: f.H + "px" }); if(window._IeVersion <= 7) { if(t.css("float") === "left") { t.css("float", "none") } } l.show() }; var j = function() { var r = document.body.scrollWidth; if(b.width() > r) { r = b.width() } l.css({ opacity: 0.3, width: r + "px", height: a(document).height() > b.height() ? a(document).height() : b.height() + "px" }) }; var g = function() { if(f.obj != null) { var s = f.obj.offset(); var r = s.left + f.oL; var t = s.top + f.obj.height() + f.oT;
				h.css({ left: r, top: t, opacity: 1 }) } }; var k = function() { j(); if(f.obj != null) { return } var u = b.scrollTop(); var s = b.scrollLeft(); var t = (b.height() - f.H) / 2 + u; var r = (b.width() - f.W) / 2 + s; if(t < f.BSize) { t = f.BSize } if(r < f.BSize) { r = f.BSize } h.css({ top: t, left: r, opacity: 1 }) };
		n();
		g();
		k();
		m("zoomIn");
		b.resize(k);
		b.scroll(j);
		p.unbind().bind("clickFix", function(r, s) { e.call(this, r, s) }).bind("click", function() { a(this).trigger("clickFix") }); if(f.autoClose) { window.setTimeout(function() { a.PageDialog.close() }, f.autoTime) } h.ready = f.ready() };
	a.PageDialog.close = function(b) { getobj("pageDialogClose").trigger("clickFix", [b]) };
	a.PageDialog.showConfirm = function(g, c) { var f = (typeof c === "function"),
			b; if(f) { b = false } var d = '<div class="PopMsgC"><s></s>' + g + '</div><div class="PopMsgbtn"><a href="javascript:void();" id="btnMsgOK" class="orangebut">确认</a>&nbsp;&nbsp;<a href="javascript:void();" id="btnMsgCancel" class="cancelBtn">取消</a></div>'; var e = function() { a("#btnMsgCancel").click(function() { a.PageDialog.close() });
			a("#btnMsgOK").click(function() { a.PageDialog.close(b); if(f) { c() } }) };
		a.PageDialog(d, { title: "提示", W: 282, H: 146, ready: e }) } })(jQuery);