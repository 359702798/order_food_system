
//格式化代码ctrl+alt+l
//模块化和非模块化(一次性全部加载所有模块）
layui.use(['table' ,'form','jquery','layer'], function () {

    var table = layui.table;//执行一个 table 实例
    var form =layui.form;
    var  $=layui.jquery;
    layer=layui.layer
    var tableIns = table.render({

        elem: '#Westtable'//需要实例化表格的id
        , height: 800
        , url: '../Food/getWest' //数据接口
        , title: '中餐表'
        , page: true //开启分页
        , totalRow: true //开启合计行
        , cols: [[ //表头
            {
                field: 'foodPhoto',
                title: '图片',
                templet: function (d) {
                    var path = d.foodPhoto;
                    console.log(path)
                    return '<img src="../' + path + '"width="100%" height="100%" />'
                }

            }

            , {field: 'foodName', title: '食物名称'}
            , {field: 'storeId', title: '店铺ID'}
            , {field: 'foodPrice', title: '食物价格'}

            , {field: 'foodType', title: '食物类型'}
            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}

        ]]
    });
    // 监听行
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'buy'){
            //
            $.ajax({
                type:'POST',
                url:'../Food/buy',
                data:{

                    'id':data.foodId
                },
                dataType:'json',
                traditional:true,//向后台传送数组的时候必须设置为true
                success:function (res) {
                    //res.video
                    layer.open( {
                            type:2,
                            title:'预览信息',
                            area:['70%','70%'],
                            content:'buy.html',
                            success: function(layero, index){
                                //获取子页面的标签和内容
                                var body = layer.getChildFrame('body', index);
                                body.find('#foodName').val(res.foodName)
                                body.find('#foodPrice').val(res.foodPrice)
                                body.find('#storeName').val(res.storeId)


                            }
                        }
                    )

                }


            })

        }
    });



});