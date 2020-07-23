new Vue({
    el: '#app',
    data: {
        userList:[],
        teacher: {}
    },
    created() {
        this.getUserList()
    },
    methods: {
        getUserList() {
            axios.get(`http://localhost:8001/eduservice/teacher/getTeacher/1`)
                .then(resp => {
                    this.teacher = resp.data.data.teacher
                    console.log(resp)
                    console.log(this.teacher)
                })
                .catch(error => {

                })
        }
    }
})