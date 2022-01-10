function onSubmit(e) {
    // hidden input in the form with the name command has empty value by default.
    // next instruction used to set this value from the name attribute (name="something")
    document.getElementsByName("command")[0].value = e.getAttribute("name")
        || e.dataset.name || e.dataset.command;
    document.getElementsByName("subCommand")[0].value = e.dataset.subCommand
    // next 4 lines not implemented yet on the backend
    let indexVar = document.getElementsByName("index")[0];
    if (indexVar !== undefined) {
        indexVar.value = e.dataset.index;
    }
    // by default form is submitted when submit button clicked, but when we click div, img or td tags
    // form will be submitted too. Next 3 lines describe this moment
    if (e.tagName === 'DIV' || e.tagName === 'IMG' || e.tagName === 'TD') {
        document.getElementById("form").submit()
    }
}