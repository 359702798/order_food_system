layui.use(['table','form','jquery','layer'], function () {
    var table = layui.table,
        form = layui.form,
        $ = layui.jquery,
        layer = layui.layer
    //执行一个table 实例
    var tableIns =table.render({
        elem: '#admintable',//需要实例化表格的id
        height: 600,
        url: '../admin/getAll' //向后台发送请求获取对应的数据
        ,   //   w为什么要写这样的请求？
        title: '管理员账号',
        page: true //开启分页
        ,
        toolbar: '#headDemo' //default表示框架的默认按钮,大多数情况需要自定义按钮
        ,
        cols: [
            [ //为什么要展示下面的这些数据？   list<video>-->转换成了json
                {
                    type: 'checkbox', //复选框
                    fixed: 'left'  //固定在最左侧
                }, {
                field: 'adminId', //必须和返回的json格式数据的key值一摸一样
                title: '管理员ID',
                sort: true, //排序
                fixed: 'left'
            }, {
                field: 'adminName',
                title: '管理员姓名'
            }, {
                field: 'adminPassword',
                title: '管理员密码'
            }, {
                field: 'adminTelephone',
                title: '管理员电话'
            },  {
                fixed: 'right',
                width: 165,
                align: 'center',
                toolbar: '#barDemo'
            }
            ]
        ]
    });










    //监听头工具栏事件
    table.on('toolbar(admintest)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id) //获取到选中的复选框
            ,datas = checkStatus.data; //获取选中的数据 是一个数组类型的值

        switch(obj.event){
            case 'add':
                layer.open({
                    type:2,
                    title:'添加管理员',
                    area:['50%','50%'],
                    content:'addadmin.html'
                })
                //     layer.open(e:{
                //         type:5,
                //         title:'添加店铺'
                //         area:['20%','20%','20%','20%','20%'],
                //          content:'addstore.html'
                // })
                //我需要进行视频上传操作--提供视频信息

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
                        ids[i] = datas[i].adminId;
                    }
                    //编写一个弹出层
                    layer.confirm("数据可贵！是否确认删除？",function (index) {
                        //index 表示当前layer弹出层的索引（可以用来关闭当前弹出层）
                        //向后台发送请求
                        $.ajax({
                            type:'POST',
                            url:'../admin/deleteById',
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

    //监听行工具事件
    table.on('tool(admintest)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var d = obj.data //获得当前行数据 （一行数据）
            ,layEvent = obj.event; //获得 lay-event 对应的值


        if(layEvent === 'del'){
            var id =d.adminId;

            var ids = new Array();
            ids[0]=id;
            layer.confirm('真的删除行么？', function(index){
                //向服务端发送删除指令
                $.ajax({
                    type:'POST',
                    url:'../admin/deleteById',
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
                url:"../admin/details",
                data:{
                    'id':d.adminId
                },
                dataType:'json',
                success:function (res) {
                    layer.open({
                        type:2,
                        title:'订单预览',
                        area:['70%','70%'],
                        content:'editorAdmin.html',
                        //页面跳转以后  需要赋值
                        success: function(layero, index){
                            //获取子页面的body标签以及内容
                            var body = layer.getChildFrame('body', index);
                            console.log(body)
                            //获取子页面的body标签以及内容
                            body.find('#adminId').val(res.adminId)
                            body.find('#adminName').val(res.adminName)
                            body.find('#adminPassword').val(res.adminPassword)
                            body.find('#adminTelephone').val(res.adminTelephone)

                        }
                    })
                }
            })
        }

    });



});