function addRow(){
    var entityTable = document.getElementById("entityTable");
    var idx = entityTable.rows.length;
    var htmlArr = [];
    htmlArr[0] = '<label>' +
        '<input type="text" class="form-control" name="entityName" placeholder="请输入属性名"/>' +
        '</label>';
    htmlArr[1] = '<label>' +
        '<select class="form-control" name="entityProperty">' +
        '<option selected="selected">请选择数据类型</option>' +
        '<option value="Integer">Integer</option>' +
        '<option value="String">String</option>' +
        '<option value="Date">Date</option>' +
        '<option value="Short">Short</option>' +
        '<option value="Byte">Byte</option>' +
        '<option value="Long">Long</option>' +
        '<option value="Double">Double</option>' +
        '<option value="Character">Character</option>' +
        '<option value="Boolean">Boolean</option>' +
        '<option value="Timestamp">Timestamp</option>' +
        '</select>' +
        '</label>';
    htmlArr[2] = '<button class="btn btn-warning" name="delete" type="button" onclick="deleteRow(this)" id="#index">删除</button>';
    var oTR = entityTable.insertRow(idx);
    var oTD;
    for (var i = 0; i < htmlArr.length; i++) {
        oTD = oTR.insertCell(i);
        oTD.innerHTML = htmlArr[i].replace(/#index/g, idx);
    }
}

function deleteRow(obj){
    var entityTable = document.getElementById("entityTable");
    var rowId = obj.getAttribute("id");
    entityTable.deleteRow(Number(rowId));
    for (var i = 0; i < entityTable.rows.length - 2; i++) {
        var idx = document.getElementsByName("delete")[i]; 
        idx.setAttribute("id",i + 2);
    }
}

function submit() {
    var entity = [];
    var entityTable = document.getElementById("entityTable");
    var primaryKey = {};
    for(var n=0; n<entityTable.rows[1].cells.length; n++){
        var primaryName = entityTable.rows[1].cells[n].children[0].children[0].name;
        if (entityTable.rows[1].cells[n].children[0].children.length === 1){
            if (entityTable.rows[1].cells[n].children[0].children[0].value === "" || entityTable.rows[1].cells[n].children[0].children[0].value === "请选择数据类型"){
                alert("请填写主键或类型");
                return;
            }else {
                primaryKey[primaryName] = entityTable.rows[1].cells[n].children[0].children[0].value;
            }
        }else {
            if (entityTable.rows[1].cells[n].children[0].children[0].checked){
                primaryKey[primaryName] = entityTable.rows[1].cells[n].children[0].children[0].value;
            }else {
                primaryKey[primaryName] = entityTable.rows[1].cells[n].children[0].children[1].value;
            }
        }

    }
    entity.push(primaryKey);
    for(var i=2; i<entityTable.rows.length; i++){
        var Key = {};
        for(var j=0; j<entityTable.rows[i].cells.length - 1; j++){
            var Name = entityTable.rows[i].cells[j].children[0].children[0].name;
            Key[Name] = entityTable.rows[i].cells[j].children[0].children[0].value;
            var auto = "entityAuto";
            Key[auto] = "false";
        }
        entity.push(Key);
    }
    var tableName = document.getElementById("tableName").value;
    $.ajax({
        type:'post',
        dataType:'json',
        url:'entity?tableName=' + tableName,
        contentType:"application/json;charset=utf-8",
        data: JSON.stringify(entity),
        success:function(data){
            if(data.code === 0){
            }
        }
    })
}
