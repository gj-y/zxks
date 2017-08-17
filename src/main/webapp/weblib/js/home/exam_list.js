/**
 * Created by 嘉尧 on 2017/4/12.
 */
$(function () {
   $.ajax({
       url: basePath + 'examCtr/get_exam_list?token=' + getCookie("token"),
       dataType: 'json',
       success: function (data) {
           var $table_body = $("#exam_list");
           for (var i = 0; i < data.exams.length; i++){
               var exam = data.exams[i];
               var html = "";
               html += '<tr>';
               html += '<td value="'+exam.id+'">'+(i + 1)+'</td>';
               html += '<td>'+exam.examname+'</td>';
               var newDate = new Date();
               newDate.setTime(exam.starttime);
               html += '<td>'+newDate.toLocaleString()+'</td>';
               html += '<td><a href="'+basePath +'examCtr/show_exam_rank?token='+ getCookie("token")+'&examid='+ exam.id +'" >详情</a></td>';
               html += '</tr>';
               $table_body.append(html);
           }
       }
   });
});