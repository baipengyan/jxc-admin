/*
* 列表页通用混入
* 引用者必须要有search方法
* */
const mixin = {
    data() {
        return {
            searchForm: {
                page: 1,
                pageSize: 15,
                total: 0
            },
            config: {
                loading: false,
                operating: false
            },
            tableData: [],
            row: null,
            type: 'see'
        }
    },
    watch: {
        row(v) {
            !v && this.$refs.table && this.$refs.table.setCurrentRow()
        }
    },
    methods: {
        pageChange(v) {
            this.searchForm.page = v
            this.search()
        },
    },
    activated() {
        this.search()
    }
}

export default mixin
