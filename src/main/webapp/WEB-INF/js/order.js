
layui.use(['table','form','jquery','layer'],function () {
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var layer = layui.layer;
    //执行一个table实例
    var tableIns = table.render({
        elem: '#ordertable',    //需要实例化表格的id
        height: 420,
        url: '../Order/getAll' //向后台发送请求获取对应的数据
        ,
        title: '视频信息',
        page: true //开启分页
        ,
        toolbar: '#headDemo' //default表示框架的默认按钮，大多数情况需要自定义按钮
        ,
        cols: [
            [ //表头
                {
                    type: 'checkbox',
                    fixed: 'left'
                }, {
                field: 'orderId',//必须和返回的JSON格式数据的key值一摸一样
                title: 'ID',
                sort: true,//排序
                fixed: 'left',//固定在最左行
                totalRowText: '合计：'
            }, {
                field: 'userName',
                title: '订单人',
            },{
                field: 'storeName',
                title: '餐厅名称',
            }, {
                field: 'foodName',
                title: '食物名字',
            }, {
                field: 'foodPrice',
                title: '单价',
            }, {
                field: 'orderCount',
                title: '数量(份)',

            }, {
                field: 'totalPrice',
                title: '总价',

            }, {
                field: 'userAddress',
                title: '用户地址',
            }, {
                field: 'orderTime',
                title: '订单时间',
            }, {
                fixed: 'right',
                width: 165,
                align: 'center',
                toolbar: '#barDemo'
            }
            ]
        ]
    });




    //监听头工具栏事件
    table.on('toolbar(ordertest)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)//获取到选中的复选框
            ,datas = checkStatus.data; //获取选中的数据     ，是一个数组类型的值
        switch(obj.event){
            case 'add':
                layer.open({
                    type:2,
                    title:'上传视频',
                    area:['60%','60%'],
                    content:'addvideo.html'
                })
                break;
            case 'delete':
                //我需要获取到我选中行的数据--（id）
                //实现批量数据删除
                if(datas.length === 0){
                    layer.msg('请选择一行');
                } else {
                    //声明一个数组，来存放选中的数据id
                    var ids = new Array()
                    for (var i in datas){
                        ids[i] = datas[i].orderId
                    }
                    //编写一个弹出层
                    layer.confirm("数据可贵，是否删除",function (index) {
                        //index表示当前layer弹出层的索引（可以用来关闭当前弹出层）
                        //向后台发送请求
                        $.ajax({
                            type:'POST',
                            url:'../Order/deleteById',
                            data:{
                                'ids':ids
                            },
                            dataType:'json',
                            traditional:true,//向后台传送数组的时候，必须设置为true
                            success:function (res) {
                                //res将会接收后台返回的成功或者失败的消息-->Result
                                if (res.code == 200){
                                    layer.alert(res.msg,{icon:1},function(index){
                                        //关闭弹框00
                                        layer.close(index)
                                        //需要去刷新表格
                                        tableIns.reload();
                                    })
                                } else{
                                    layer.alert(res.msg,{icon:2},function (index) {
                                        layer.close(index)
                                    })
                                }

                            }


                        })
                    })
                }
                break;
        };
    });






    //监听行工具事件
    table.on('tool(ordertest)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值

        var id = data.orderId;
        var ids = new Array();
        ids[0] = id;

        if(layEvent === 'detail'){
            //需要向后台重新获取详情信息
            $.ajax({
                type:'POST',
                url:"../Order/details",
                data:{
                    'id':data.orderId
                },
                dataType:'json',
                success:function (res) {
                    layer.open({
                        type:2,
                        title:'订单预览',
                        area:['70%','70%'],
                        content:'detailOrder.html',
                        //页面跳转以后  需要赋值
                        success: function(layero, index){
                            //获取子页面的body标签以及内容
                            var body = layer.getChildFrame('body', index);
                            console.log(body)
                            //获取子页面的body标签以及内容
                            body.find('#orderId').val(res.orderId)
                            body.find('#userName').val(res.userName)
                            body.find('#storeName').val(res.storeName)
                            body.find('#foodName').val(res.foodName)
                            body.find('#foodPrice').val(res.foodPrice)
                            body.find('#orderCount').val(res.orderCount)
                            body.find('#totalPrice').val(res.totalPrice)
                            body.find('#userAddress').val(res.userAddress)
                            body.find('#orderTime').val(res.orderTime)

                        }
                    })
                }
            })



        } else if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){

                //向服务端发送删除指令
                $.ajax({
                    type:'POST',
                    url:'../Order/deleteById',
                    data:{
                        'ids':ids
                    },
                    dataType:'json',
                    traditional:true,//向后台传送数组的时候，必须设置为true
                    success:function (res) {
                        //res将会接收后台返回的成功或者失败的消息-->Result
                        if (res.code == 200){
                            layer.alert(res.msg,{icon:1},function(index){
                                //关闭弹框00
                                layer.close(index)
                                //需要去刷新表格
                                tableIns.reload();
                            })
                        } else{
                            layer.alert(res.msg,{icon:2},function (index) {
                                layer.close(index)
                            })
                        }

                    }


                })
            });
        }else if (layEvent === 'edit'){
            //需要向后台重新获取详情信息
            $.ajax({
                type:'POST',
                url:"../Order/details",
                data:{
                    'id':data.orderId
                },
                dataType:'json',
                success:function (res) {
                    layer.open({
                        type:2,
                        title:'订单预览',
                        area:['70%','70%'],
                        content:'editor.html',
                        //页面跳转以后  需要赋值
                        success: function(layero, index){
                            //获取子页面的body标签以及内容
                            var body = layer.getChildFrame('body', index);
                            console.log(body)
                            //获取子页面的body标签以及内容
                            body.find('#orderId').val(res.orderId)
                            body.find('#userName').val(res.userName)
                            body.find('#storeName').val(res.storeName)
                            body.find('#foodName').val(res.foodName)
                            body.find('#foodPrice').val(res.foodPrice)
                            body.find('#orderCount').val(res.orderCount)
                            body.find('#totalPrice').val(res.totalPrice)
                            body.find('#userAddress').val(res.userAddress)
                            body.find('#orderTime').val(res.orderTime)

                        }
                    })
                }
            })
        }
    });






})