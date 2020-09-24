var FormValidation = {
    username: '^[a-zA-Z0-9_]{3,30}$',
    alphanumberic: '^[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s ]*$',
    specialCharacters: '[$&+:;=?@#|~`<>^*()%!]',
    address: '[$&+:;=?@#|~`<>^*()%!]',

}


$(document).ready(function() {
   $.validator.addMethod(
        "username",
        function(value, element, isActive) {
            if(!isActive) {
                return true;
            }
            var re = new RegExp(FormValidation.username);
            return this.optional(element) || re.test(value);
        },
        "Tên tài khoản chỉ chứa chữ cái, chữ số, gạch dưới và có 3-30 ký tự"
    );
    $.validator.addMethod(
        "alphanumberic",
        function(value, element, isActive) {
            if(!isActive) {
                return true;
            }
            var re = new RegExp(FormValidation.alphanumberic);
            return this.optional(element) || re.test(value);
        },
        "Dữ liệu không hợp lệ"
    );
    $.validator.addMethod(
        "ignoreSpecial",
        function(value, element, isActive) {
            if(!isActive) {
                return true;
            }
            var re = new RegExp(FormValidation.specialCharacters);
            return this.optional(element) || !re.test(value);
        },
        "Dữ liệu không được chứa các ký tự đặc biệt"
    );
    $.validator.addMethod(
        "address",
        function(value, element, isActive) {
            if(!isActive) {
                return true;
            }
            var re = new RegExp(FormValidation.address);
            return this.optional(element) || !re.test(value);
        },
        "Địa chỉ có ký tự không hợp lệ"
    );
    // Form add
	$("#formAdd").validate({
	    ignore: [],
        errorElement: 'small',
        errorClass: 'text text-danger',
		rules: {
			menuName: {
				required: true,
//                username: true,
                alphanumberic: true,
			},
			menuSlug: {
				required: true,
			},
			menuIcon: {
				required: false,
//				ignoreSpecial: true
			},
			menuDescription: {
                ignoreSpecial: true
			}

		},
		messages: {
			menuName: {
				required: "Menu name không được bỏ trống",
			},
			menuSlug: {
				required: "URL không được bỏ trống",
			},
		}
	});
	// Form edit
	$("#formEdit").validate({
    	    ignore: [],
            errorElement: 'small',
            errorClass: 'text text-danger',
    		rules: {
    			menuName: {
    				required: true,
                    alphanumberic: true,
    			},
    			menuSlug: {
    				required: true,
    			},
    			menuIcon: {
    				required: false,
    //				ignoreSpecial: true
    			},
    			menuDescription: {
                    ignoreSpecial: true
    			}

    		},
    		messages: {
    			menuName: {
    				required: "Menu name không được bỏ trống",
    			},
    			menuSlug: {
    				required: "URL không được bỏ trống",
    			},
    		}
    	});
});
