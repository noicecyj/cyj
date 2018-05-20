function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="btn btn-default edit" style="margin-right:15px;">编辑</button>',
        '<button type="button" class="btn btn-default delete" style="margin-right:15px;">删除</button>'
    ].join('');
}



window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        $("#editModal").modal('show');
    },
    'click .delete': function (e, value, row, index) {
        $("#myModal").modal('show');
    }
};

