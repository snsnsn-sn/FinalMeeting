function serialize(form) {
    var parts = [],
        elems = form.elements,
        i = 0,
        len = elems.length,
        filed = null;
    for (; i < len; i++) {
        filed = elems[i];
        switch (filed.type) {
            case "select-one":
            case "select-multiple":
                if (filed.name.length) {
                    var j = 0,
                        opt,
                        optLen = filed.options.length;
                    for (; j < optLen; j++) {
                        opt = filed.options[j];
                        if (opt.selected) {
                            parts.push(encodeURIComponent(filed.name) + "=" + encodeURIComponent(opt.value));
                        }
                    }
                }
                break;
            case undefined:
            case "submit":
            case "reset":
            case "file":
            case "button":
                break;
            case "radio":
            case "checkbox":
                if (!filed.checked) {
                    break;
                }
            default:
                if (filed.name.length && filed.value) {
                    parts.push(encodeURIComponent(filed.name) + "=" + encodeURIComponent(filed.value));
                }
        }
    }
    return parts.join("&");
}