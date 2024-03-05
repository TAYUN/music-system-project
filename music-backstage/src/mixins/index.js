export const mixin = {
	methods: {
		// 封装消息弹窗
		notify(title, type) {
			this.$notify({
				title: title,
				type: type,
			});
		},
		// 将相对地址换成绝对地址
		getUrl(url) {
			// 这里${url}前面不用加'/'
			return `${this.$store.state.HOST}${url}`;
		},
		//将性别1 、 0 性别转换成中文
		changeSex(value) {
			if (value == 0) {
				return '女';
			}
			if (value == 1) {
				return '男';
			}
			if (value == 2) {
				return '组合';
			}
			if (value == 3) {
				return '不明';
			}
			return value;
		},
        //生日格式转换
        attachBirth(val){
            return String(val).substring(0,10);
        },

        //上传图片之前的校验
        beforeAvatorUpload(file){
            const isJPG = (file.type === 'image/jpeg')||(file.type === 'image/png');
            if(!isJPG){
                this.$message.error('上传头像图片只能是jpg或png格式');
                return false;
            }
            const isLt2M = (file.size / 1024 / 1024) < 2;
            if(!isLt2M){
                this.$message.error('上传头像图片大小不能超过2MB');
                return false;
            }
            return true;
        },
        //上传歌手图片成功之后要做的工作
        handleAvatorSuccess(res){
            console.log('res : ',res)
            let _this = this;
            if(res.code == 1){
                _this.getData();
                _this.$notify({
                    title: '上传成功',
                    type: 'success'
                });
            }else{
                _this.$notify({
                    title: '上传失败',
                    type: 'error'
                });
            }
        },
        //弹出删除窗口
        handleDelete(id){
            this.idx = id;
            this.delVisible = true;
        },
        //把已经选择的项赋值给multipleSelection
        handleSelectionChange(val){
            this.multipleSelection = val;
        },
        //批量删除已经选择的项
        delAll(){
            //遍历被选中的元素，循环删除
            for(let item of this.multipleSelection){
                //弹出删除窗口
                this.handleDelete(item.id);
                this.deleteRow();
            }
            this.multipleSelection = [];
        }
	},
};
