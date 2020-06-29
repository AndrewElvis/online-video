new Vue({
    el: '#app',
    data: function() {
        return {
            video: {
                cover: "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
                title: '',
                sourceId: '',
                originalName:''//视频名称
            },
            saveBtnDisabled: false, // 保存按钮是否禁用
            BASE_API: 'http://127.0.0.1:8002',
            fileList: []
        }
    },
    methods: {
        //点击确定调用的方法
        handleVodRemove() {
            //调用接口的删除视频的方法
            axios.delete('http://127.0.0.1:8002/vod/removeAliVideo/'+this.video.sourceId)
                .then(response => {
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '删除视频成功!'
                    });
                    //把文件列表清空
                    this.fileList = [];
                    //把video视频id和视频名称值清空
                    //上传视频id赋值
                    this.video.sourceId = '';
                    //上传视频名称赋值
                    this.video.originalName = ''
                })
        },
        //点击×调用这个方法
        beforeVodRemove(file,fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        },
        //上传视频成功调用的方法
        handleVodUploadSuccess(response, file, fileList) {
            //上传视频id赋值
            this.video.sourceId = response.data.videoId;
            //上传视频名称赋值
            this.video.originalName = file.name
        },
        handleUploadExceed() {
            this.$message.warning('想要重新上传视频，请先删除已上传的视频')
        },
        saveVideo() {
            axios.post('http://127.0.0.1:8002/video/',{data: this.video})
        }
    }
})