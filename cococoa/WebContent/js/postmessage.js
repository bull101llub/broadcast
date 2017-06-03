    // 行選択時のsubmit
    function update() {
        var postuser = document.getElementById("postname").value;
        var c0010 =  "";
        var c0011 =  "";
        var c0012 =  "";
        var c0013 =  "";

        if (document.getElementById("C0010")) {
        	c0010 = document.getElementById("C0010").value;
        }
        if (document.getElementById("C0011")) {
        	c0011 =  document.getElementById("C0011").value;
        }
        if (document.getElementById("C0012")) {
        	c0012 =  document.getElementById("C0012").value;
        }
        if (document.getElementById("C0013")) {
        	c0013 =  document.getElementById("C0013").value;
        }

        if("" == c0010 && "" == c0011 && "" == c0012 && "" == c0013) {
            alert("いずれかの項目にコメントしてください。");
            return;
        }

    	if("" == postuser) {
            alert("ラジオネームを入力してください。");
            return;
    	}

    	if (!dispflg()) {
            alert("入力可能な文字数を超えています。");
    		return;
    	}


    	if(window.confirm('投稿します。よろしいですか？')){
            var form = document.getElementById("postform");
            form.action="Message";
            form.method = "post";
            setParam(form, "key", "create");

            form.submit();
    	}
    }

    function setParam(form, paramName, value) {
        var input = document.createElement('input');
        input.setAttribute('type', 'hidden');
        input.setAttribute('name', paramName);
        input.setAttribute('value', value);
        form.appendChild(input);
    }

    var limitM=250; //最大文字数
    var limitS=0;  //最小文字数

    function lengthChk(obj){
        var contentsid = obj.getAttribute("id");
        var id = obj.getAttribute("id");
        var str = document.getElementById(id).value;


        val=str.replace(/\s|　/gm,'').length;//空白文字を除いた文字数

        //文字数表示
        document.getElementById('msg1' + contentsid).innerHTML= "<span style='color:blue ;'>"+val+"</span>";

        //制限文字数を超えた時 max()を実行
        obj.setAttribute("isover", "false");
        if(val>limitM){
            max(contentsid);
            obj.setAttribute("isover", "true");
            return;
        }

        //制限文字数と文字数が同じなら"制限文字数です"を表示
        if(val==limitM){
            document.getElementById('msg2' + contentsid).innerHTML = "<span style='color:red;'></span>";
        } else {
            //残り文字数表示
            document.getElementById('msg2' + contentsid).innerHTML = " <span style='bold;color:blue ;'>"
            +(limitM-val)+"</span>";
        }
    }

    function max(contentsid){ //入力文字数が制限文字数を超えた時
        //超えた文字数表示
        document.getElementById('msg2' + contentsid).innerHTML = " <span style='color:red;'>"
        +Math.abs(limitM-val)+"</span>";
    }

    function dispflg(){
       var elements =  document.getElementsByTagName("textarea");
       var flg = "false";

       for (var i = 0; i < elements.length; ++i) {
           flg = elements[i].getAttribute("isover");
           if ("true" == flg) {
               return false;
           }
       }
       return true;
    }

