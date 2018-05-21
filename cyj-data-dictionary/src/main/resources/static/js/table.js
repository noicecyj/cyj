function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="btn btn-default editCatalog" style="margin-right:15px;">编辑内容</button>',
        '<button type="button" class="btn btn-default edit" style="margin-right:15px;">编辑</button>',
        '<button type="button" class="btn btn-warning deleteCatalog" style="margin-right:15px;">' +
        '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button>'
    ].join('');
}

function operateFormatter1(value, row, index) {
    return [
        '<button type="button" class="btn btn-default editDictionary" style="margin-right:15px;">编辑</button>',
        '<button type="button" class="btn btn-warning deleteDictionary" style="margin-right:15px;">' +
        '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button>'
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
        }
    });
}

function hasDictionary(id){
    var size = 0;
    $.ajax({
        type : "GET",
        url : '/dictionary?pid=' + id,
        async: false,
        contentType: "application/json;charset=utf-8",
        dataType:"json",
        success : function (msg) {
            size = msg.length;
        }
    });
    return size;
}

function saveCatalog(){
    var catalogName = document.getElementById("catalogName").value;
    var catalogValue = document.getElementById("catalogValue").value;
    var description = document.getElementById("description").value;
    $.ajax({
        type : "GET",
        url : '/saveDictionaryCatalog',
        data: {
            catalogName: catalogName,
            catalogValue:catalogValue,
            description:description
        },
        contentType: "application/json;charset=utf-8",
        dataType:"json"
    });
    window.location.reload();
}

function updateCatalog(){
    var editCatalogId = document.getElementById("editCatalogId").value;
    var editCatalogName = document.getElementById("editCatalogName").value;
    var editCatalogValue = document.getElementById("editCatalogValue").value;
    var editDescription = document.getElementById("editDescription").value;
    $.ajax({
        type : "GET",
        url : '/updateDictionaryCatalog',
        data: {
            editCatalogId:editCatalogId,
            editCatalogName: editCatalogName,
            editCatalogValue:editCatalogValue,
            editDescription:editDescription
        },
        contentType: "application/json;charset=utf-8",
        dataType:"json"
    });
    window.location.reload();
}

function saveDictionary(){
    var catalogId = document.getElementById("catalogId1").value;
    var dictionaryName = document.getElementById("dictionaryName").value;
    var dictionaryValue = document.getElementById("dictionaryValue").value;
    $.ajax({
        type : "GET",
        url : '/saveDictionary',
        data: {
            catalogId:catalogId,
            dictionaryName: dictionaryName,
            dictionaryValue: dictionaryValue
        },
        contentType: "application/json;charset=utf-8",
        dataType:"json"
    });
    window.location.reload();
}

function updateDictionary(){
    var editDictionaryId = document.getElementById("editDictionaryId").value;
    var editCatalogId = document.getElementById("editCatalogId1").value;
    var editDictionaryName = document.getElementById("editDictionaryName").value;
    var editDictionaryValue = document.getElementById("editDictionaryValue").value;
    $.ajax({
        type : "GET",
        url : '/updateDictionary',
        data: {
            editDictionaryId:editDictionaryId,
            editCatalogId:editCatalogId,
            editDictionaryName: editDictionaryName,
            editDictionaryValue:editDictionaryValue
        },
        contentType: "application/json;charset=utf-8",
        dataType:"json"
    });
    window.location.reload();
}


window.operateEvents = {
    'click .editCatalog': function (e, value, index) {
        document.getElementById("catalogId1").value = index.id;
        $('#dictionaryTable').bootstrapTable({
            ajax: dictionary(index.id)
        });
        $("#editModal").modal('show');
    },
    'click .edit': function (e, value, index) {
        var id = index.id;
        var catalogName = index.catalogName;
        var catalogValue = index.catalogValue;
        var description = index.description;
        document.getElementById("editCatalogId").value = id;
        document.getElementById("editCatalogName").value = catalogName;
        document.getElementById("editCatalogValue").value = catalogValue;
        document.getElementById("editDescription").value = description;
        $("#editCatalogModal").modal('show');
    },
    'click .deleteCatalog': function (e, value, index) {
        var catalogSize = hasDictionary(index.id);
        if (catalogSize === 0) {
            if(confirm("确认删除吗?")) {
                $('#dictionaryCatalogTable').bootstrapTable('remove', {
                    field: 'mainContentId',
                    values: [index.id]
                });
                $.ajax({
                    type: "GET",
                    url: '/deleteCatalog',
                    data: {
                        catalogId: index.id
                    },
                    contentType: "application/json;charset=utf-8",
                    dataType: "json"
                });
            }
        }else {
            alert("目录中存在数据，不能删除！");
        }
        window.location.reload();
    }
};

window.operateEvents1 = {
    'click .editDictionary': function (e, value, index) {
        var id = index.id;
        var catalogId = index.dictionaryCatalog.id;
        var dictionaryName = index.dictionaryName;
        var dictionaryValue = index.dictionaryValue;
        document.getElementById("editDictionaryId").value = catalogId;
        document.getElementById("editCatalogId1").value = id;
        document.getElementById("editDictionaryName").value = dictionaryName;
        document.getElementById("editDictionaryValue").value = dictionaryValue;
        $("#updateDictionaryModal").modal('show');
    },
    'click .deleteDictionary': function (e, value, index) {
        if(confirm("确认删除吗?")) {
            $('#dictionaryTable').bootstrapTable('remove', {
                field: 'mainContentId',
                values: [index.id]
            });
            $.ajax({
                type: "GET",
                url: '/deleteDictionary',
                data: {
                    dictionaryId: index.id
                },
                contentType: "application/json;charset=utf-8",
                dataType: "json"
            });
        }
        window.location.reload();
    }
};