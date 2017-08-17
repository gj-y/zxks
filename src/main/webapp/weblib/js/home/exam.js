$(function () {
    var examUtil = new MyExam();
    examUtil.loadProblems();
    examUtil.loadCountdown();

    $("#next_problem").click(function () {

     //   $('.tab-pane.active').next().addClass('active').siblings().removeClass('active');
        examUtil.nextProblem();

    });
    $("#pre_problem").click(function () {

      //  $('.tab-pane.active').prev().addClass('active').siblings().removeClass('active');
        examUtil.preProblem();
    });

    $("#put_test").click(function () {
        examUtil.putAnswer();
    });

    var $language_item = $(".lanuage_item");
    $("#lanMenu").val($language_item.eq(0).attr("value"));
    $language_item.click(function () {
        var $lanMenu = $("#lanMenu");
        $lanMenu.html($(this).eq(0).html()+'<span class="caret"></span>');
        $lanMenu.val($(this).attr("value"));
    });
});


function MyExam() {

    this.problems = [];
    this.index = 0;
    this.editors = {};
    this.examinfo = {};
    this.timeleft = 0;


    this.nextProblem = function () {
        $('.tab-pane.active').next().addClass('active').siblings().removeClass('active');
        if( this.index < (this.problems.length -1) ){
            this.index++;
        }
        this.updateProgres();
    };
    this.preProblem = function () {
        $('.tab-pane.active').prev().addClass('active').siblings().removeClass('active');
        if(this.index > 0){
            this.index--;
        }
        this.updateProgres()
    };
    this.updateProgres = function() {
        var $progresBar = $("#exam_progres");
        var percent = (this.index+1)/this.problems.length;
        var em = percent * 92;
        $progresBar.css("min-width", em + "em");
        $progresBar.html(Math.floor(percent*100) + "%");
    };
    this.putAnswer = function () {

        var answer = {};
        //参数
        answer.lanuage = $("#lanMenu").val();
        answer.codes = [];
        //代码
        var index = 0;
        var ids = $(".problem_id");
        for (var i in this.editors) {
            if(this.editors.hasOwnProperty(i)){
                answer.codes[index] = {};
                answer.codes[index].id = ids.eq(index).val();
                answer.codes[index].code = this.editors[i].getValue();
                index++;
            }
        }
        console.log(answer);

        $.ajax({
            url: basePath + "examCtr/put_test?token=" + getCookie("token"),
            data: {'answer':JSON.stringify(answer), 'testid': testid},
            dataType: 'json',
            success: $.proxy(function (data) {
                window.location.href = basePath + 'examCtr/show_exam_rank?token='+ getCookie("token") +'&examid='+ data.examid;
            },this)
        });
    };
    this.newPaperHtml = function (data) {
        var problem_number = data.length;
        var html = '';
        for (var i = 0; i < problem_number; i++) {
            html = '';
            var problem = data[i];
            if(i != 0 ) {
                html += '<div class="tab-pane" id="problem'+ i +'"><div class="col-md-12"><div>';
            }else {
                html += '<div class="tab-pane active" id="problem'+ i +'"><div class="col-md-12"><div>';
            }
            html += '<input type="hidden" class="problem_id" value="'+problem.problemId+'"/>';
            html += '<p class="text-center" id="problem_title'+i+'">'+ problem.title +'</p></div>';
            html += '<div><p id="problem_description'+i+'">题目描述：'+ problem.description +'</p></div></div><div class="col-md-12">';
            html += '<textarea class="form-control" rows="12" style="resize: none;" id="code'+i+'" >';
            html += '</textarea></div><div class="col-md-6"><span>样例输入</span>';
            html += '<textarea id="problem_samplein'+i+'" class="form-control" rows="4" style="resize: none;" disabled>'+ problem.sampleInput
                +'</textarea>';
            html += '</div><div class="col-md-6"><span>样例输出</span>';
            html += '<textarea id="problem_sampleout‘+i+’" class="form-control" rows="4" style="resize: none;" disabled>'+
                problem.sampleOutput +'</textarea>';
            html += '</div></div>';
            $("#problems").append(html);
            var temp = $('<li role="presentation"><a  href="#problem'+ i +'">Home</a></li>');
            if( i == 0){
                temp.addClass("active");
            }
            $("#myTabs").append(temp);
            this.editors['editor' + i] = CodeMirror.fromTextArea(
                document.getElementById("code" + i),
                {
                    mode: "javascript",
                    lineNumbers: true,
                    value: 'int main(){ return 0;}'
                });
        }
    };

    this.loadProblems = function () {
        $.ajax({
            url: basePath + "examCtr/get_problems?token=" + getCookie("token") + "&testid=" + testid,
            dataType: 'json',
            success: $.proxy(function (data) {
                console.log(data);
                this.problems = data;
                this.newPaperHtml(data);
                this.updateProgres();
            },this)
        });
    };

    this.loadCountdown = function () {
        $.ajax({
            url: basePath + "examCtr/get_proceeding_exam?token=" + getCookie("token"),
            dataType: 'json',
            success: $.proxy(function (data) {
                this.examinfo = data.examinfo;
                console.log(this);
                var now = new Date();
                var timeused = now.getTime() - this.examinfo.starttime;
                timeused /= 1000;
                var timeleft = this.examinfo.time*60 - timeused;
                this.timeleft = timeleft;
                var $time_left = $(".time_left").find("span").eq(1);
                $time_left.html(this.CountDownFormat(timeleft));
                setInterval($.proxy(function () {
                    this.timeleft -= 1;
                    if(this.timeleft > 0){
                        $time_left.html(this.CountDownFormat(this.timeleft));
                        if(this.timeleft == 300){
                            alert("考试时间只剩下5分钟拉!");
                        }
                    }else{
                        this.putAnswer();
                    }
                }, this), 1000);
            },this)
        });
    }


    this.CountDownFormat = function(timeleft) {
        var time = {
            sec : 0,
            min : 0,
            hour : 0
        }
        if( timeleft < 60){
            time.sec = timeleft;
        }else{
            time.min = timeleft/60;
            time.sec = timeleft%60;
            if( time.min >= 60){
                time.hour = time.min/60;
                time.min = time.min%60;
            }
        }
        return Math.floor(time.hour) + ":" + Math.floor(time.min) + ":" + Math.floor(time.sec);
    }
}




