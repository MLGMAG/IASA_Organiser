function openPlaceForm() {
    const placeTitle = document.querySelector("#placeTitle");
    placeTitle.removeAttribute("hidden")

    const placeForm = document.querySelector("#placeForm");
    placeForm.removeAttribute("hidden");

    const addFormButton = document.querySelector("#addFormButton");
    addFormButton.setAttribute("hidden", "");

    const removeFormButton = document.querySelector("#removeFormButton");
    removeFormButton.removeAttribute("hidden");

}

function removePlaceForm() {
    const placeTitle = document.querySelector("#placeTitle");
    placeTitle.setAttribute("hidden", "")

    const placeForm = document.querySelector("#placeForm");
    placeForm.setAttribute("hidden", "");

    const addFormButton = document.querySelector("#addFormButton");
    addFormButton.removeAttribute("hidden");

    const removeFormButton = document.querySelector("#removeFormButton");
    removeFormButton.setAttribute("hidden", "");

}
