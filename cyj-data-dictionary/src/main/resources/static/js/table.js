function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="btn btn-default edit" style="margin-right:15px;">编辑</button>',
        '<button type="button" class="btn btn-default delete" style="margin-right:15px;">删除</button>'
    ].join('');
}

function dictionary(id){
    $.ajax({
        type : "GET",
        url : '/dictionary?pid=' + id,
        contentType: "application/json;charset=utf-8",
        dataType:"json",
        success : function (msg) {
            $('#dictionaryTable').bootstrapTable('load', msg);
        },
        error:function(msg){
            alert("错误");
        }
    });
}

function save(){
    var catalogName = document.getElementById("catalogName").value;
    var catalogValue = document.getElementById("catalogValue").value;
    var description = document.getElementById("description").value;
    $.ajax({
        type : "GET",
        url : '/saveDictionaryCatalog',
        data: {catalogName: catalogName,catalogValue:catalogValue,description:description},
        contentType: "application/json;charset=utf-8",
        dataType:"json",
        success : function () {
            alert("成功");
        },
        error:function(){
            alert("错误");
        }
    });
    window.location.reload();
}

window.operateEvents = {
    'click .edit': function (e, value, index) {
        $('#dictionaryTable').bootstrapTable({
            ajax: dictionary(index.id),
            columns: [{
                field: 'id',
                title: '序号',
                width: '5%'
            }, {
                field: 'dictionaryName',
                title: '键',
                width: '35%'
            }, {
                field: 'dictionaryValue',
                title: '值',
                width: '35%'
            }, {
                field: 'Button',
                title: '操作',
                events: 'operateEvents',
                formatter: 'operateFormatter'
            } ]
        });
        $("#editModal").modal('show');
    },
    'click .delete': function (e, value, row, index) {
        $("#myModal").modal('show');
    }
};

