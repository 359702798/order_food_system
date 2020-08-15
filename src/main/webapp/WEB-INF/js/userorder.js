
//格式化代码ctrl+alt+l
//模块化和非模块化(一次性全部加载所有模块）
layui.use(['table' ,'form','jquery','layer'], function () {

    var table = layui.table;//执行一个 table 实例
    var form =layui.form;
    var  $=layui.jquery;
    layer=layui.layer
    var tableIns = table.render({

        elem: '#usertable'//需要实例化表格的id
        , height: 800
        , url: '../Order/getUserOrder' //数据接口
        , title: '中餐表'
        , page: true //开启分页
        , totalRow: true //开启合计行
        , cols: [[ //表头
            {
                field: 'orderId',
                title: '订单ID',
            },
            {

                field: 'storeName',
                title: '商店名称',


            }

            , {field: 'foodName', title: '食物名称'}

            , {field: 'foodPrice', title: '食物价格'}

            , {field: 'orderCount', title: '数量'}
            , {field: 'totalPrice', title: '总价'}
            , {field: 'userAddress', title: '订单地址'}
            , {field: 'orderTime', title: '下单时间'}
            , {field: 'userName', title: '订单人'}

            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}

        ]]
    });




});