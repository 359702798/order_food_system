layui.use(['table', 'form', 'jquery'], function () {
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var tavleIns =table.render({
        elem: '#foodtable'
        , height: 600
        , url: '../Food/getAll' //数据接口
        , title: '菜品信息'
        , page: true //开启分页
        , toolbar: '#headDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , cols: [
            [ //表头

                {
                    type: 'checkbox',
                    fixed: 'left'
                },
                {
                    field: 'foodId',
                    title: '食物ID'
                },{
                field: 'foodPhoto',
                title: '食物图片',
                templet: function (d) {
                    var path = d.foodPhoto;
                    console.log(path)
                    return '<img src="../' + path + '"width="300px" height="30px" />'
                }
            }

                , {field: 'foodName', title: '食物名称'}
                , {field: 'storeId', title: '店铺ID'}
                , {field: 'foodPrice', title: '食物价格'}

                , {field: 'foodType', title: '食物类型'}
                , {
                field: 'foodState', title: '食物状态',
                templet: function (d) {
                    var s = d.foodState;
                    var vid = d.foodId;
                    if (s == 1) {
                        return '<input type="checkbox" value="' + vid + '" checked name="state" lay-skin="switch" lay-text="上线|下线" lay-filter="foodState" >'
                    } else if (s == 0) {
                        return '<input type="checkbox"  value="' + vid + '"name="state" lay-skin="switch" lay-text="上线|下线" lay-filter="foodState" >'
                    }

                }

            }
                , {fixed: 'right', width: 200, align: 'center', toolbar: '#barDemo'}

            ]
        ]
    });
    //监听状态操作
    form.on('switch(foodState)', function (data) {
        console.log(data.elem); //得到checkbox原始DOM对象
        console.log(data.elem.checked); //开关是否开启，true或者false
        console.log(data.value); //开关value值，也可以通过data.elem.value得到
        //得到美化后的DOM对象
        var check = data.elem.checked == true ? 1 : 0;
        var vid = data.value;
        $.ajax({
            type: 'post',
            url: '../Food/updateFoodState',
            data: {
                'status': check,
                'id': vid
            },
            dataType: 'json',
            success: function (res) {
                layer.msg(res.msg)
            }

        });
    });



    //监听行工具事件
    table.on('tool(foodtest)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var d = obj.data //获得当前行数据 （一行数据）
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'del'){
            var id =d.foodId;
            var ids = new Array();
            ids[0]=id;
            layer.confirm('真的删除行么？', function(index){
                //向服务端发送删除指令
                $.ajax({
                    type:'POST',
                    url:'../Food/deleteById',
                    data:{
                        'ids':ids
                    },
                    dataType:'json',
                    traditional:true,//向后台传送数组的时候，必须设置为true
                    success:function (res) {
                        //res将会接收后台返回的成功或者失败的消息-->Result
                        if (res.code == 200){
                            layer.msg(res.msg)
                            obj.del(); //删除对应行（tr）的DOM结构
                            layer.close(index);
                        } else{
                            layer.msg(res.msg);
                        }
                    }
                })

            });
        }
        else if(layEvent === 'edit')
        {
            $.ajax({
                type:'POST',
                url:'../Food/details',
                data:{
                    'id':d.foodId
                },
                dataType:'json',
                success:function (res) {
                    layer.open({
                        type:2,
                        title:'订单预览',
                        area:['70%','70%'],
                        content:'editorFood.html',
                        //页面跳转以后  需要赋值
                        success: function(layero, index){
                            //获取子页面的body标签以及内容
                            var body = layer.getChildFrame('body', index);
                            console.log(body)
                            //获取子页面的body标签以及内容
                            body.find('#foodId').val(res.foodId)
                            body.find('#storeId').val(res.storeId)
                            body.find('#foodName').val(res.foodName)
                            body.find('#foodPrice').val(res.foodPrice)
                            body.find('#foodType').val(res.foodType)
                            body.find('#foodState').val(res.foodState)
                        }
                    })
                }
            })
            //编辑

        }

    });



    //监听头工具栏事件
    table.on('toolbar(foodtest)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id) //获取到选中的复选框
            ,datas = checkStatus.data; //获取选中的数据 是一个数组类型的值

        switch(obj.event){
            case 'add':
                layer.open({
                    type:2,
                    title:'添加菜品',
                    area:['50%','50%'],
                    content:'addfood.html'
                })
                break;
            case 'delete':
                //我需要获取到我选中行的数据 --（id）
                //实现批量删除
                if(datas.length === 0){
                    layer.msg('请选择一行');
                } else {
                    //声明一个数组，来存放选中的数据id
                    var ids = new Array()
                    for (var i in datas){
                        ids[i] = datas[i].foodId;
                    }
                    //编写一个弹出层
                    layer.confirm("数据可贵！是否确认删除？",function (index) {
                        //index 表示当前layer弹出层的索引（可以用来关闭当前弹出层）
                        //向后台发送请求
                        $.ajax({
                            type:'POST',
                            url:'../Food/deleteById',
                            data:{
                                'ids':ids
                            },
                            dataType:'json',
                            traditional:true,//向后台传送数组的时候，必须设置为true
                            success:function (res) {
                                //res将会接收后台返回的成功或者失败的消息-->Result
                                if (res.code == 200){
                                    layer.alert(res.msg,{icon:1},function(index){
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


});
