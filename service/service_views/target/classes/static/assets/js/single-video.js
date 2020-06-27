new Vue({
    el: '#app',
    data: function() {
        return {
            BASE_API: '"http://localhost:9001"',
            options: [{
                value: 'sights',
                label: '风景'
            }, {
                value: 'entertainment',
                label: '娱乐'
            }, {
                value: 'food',
                label: '美食'
            }],
            value: [],
            fileList: [],
            videoUrl: ''
        }
    },
    created() {

    },
    methods: {
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        init() {
            if (this.$route.params && this.$route.params.id) {
                const id = this.$route.params.id;
                this.getPlayUrl(id);
                console.log(this.video)
            }
        },
        getPlayUrl(id) {
            axios.get(BASE_API + '/vod/getPlayUrl/' + id)
                .then(resp => {
                    videoUrl = resp.data.playUrl;
                })
                .catch(err => {

                })
        }
    }
})