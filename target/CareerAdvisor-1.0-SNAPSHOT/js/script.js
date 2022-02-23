let arrow = document.querySelectorAll(".arrow");
for (let i = 0; i < arrow.length; i++) {
    arrow[i].addEventListener("click", (e) => {
        let arrowParent = e.target.parentElement.parentElement; //selecting main parent of arrow
        arrowParent.classList.toggle("showMenu");
    });
}

let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".bx-menu");
console.log(sidebarBtn);
sidebarBtn.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});

function loadHtml(id, filename) {
    let xhttp;
    let element = document.getElementById(id);
    let file = filename;
    if (file) {
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    element.innerHTML = this.responseText;
                }
                if (this.status == 400) {
                    element.innerHTML = "<h1>Page not found.</h1>";
                }
            }
        };

        xhttp.open("GET", `/${file}`, true);
        xhttp.send();
        return;
    }
}
