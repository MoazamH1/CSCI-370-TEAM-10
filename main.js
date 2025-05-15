
document.addEventListener("DOMContentLoaded", function () {
  const button = document.getElementById("updateButton");

  button.addEventListener("click", function () {
    const input = document.getElementById("riskValue");
    const risk = parseFloat(input.value);
    const marker = document.getElementById("riskMarker");
    const bar = document.getElementById("severityBar");
    const container = document.querySelector(".severity-container");

    if (isNaN(risk) || risk < 0 || risk > 1) {
      alert("Please enter a valid risk score between 0 and 1.");
      return;
    }

    const percentage = Math.min(Math.max(risk, 0), 1) * 100;
    marker.style.left = `${percentage}%`;

    container.classList.remove("low-risk", "moderate-risk", "high-risk", "critical-risk");

    if (risk < 0.25) {
      bar.style.background = "linear-gradient(to right, #2ecc71, #2ecc71)";
      container.classList.add("low-risk");
    } else if (risk < 0.50) {
      bar.style.background = "linear-gradient(to right, #f1c40f, #f1c40f)";
      container.classList.add("moderate-risk");
    } else if (risk < 0.75) {
      bar.style.background = "linear-gradient(to right, #e67e22, #e67e22)";
      container.classList.add("high-risk");
    } else {
      bar.style.background = "linear-gradient(to right, #e74c3c, #e74c3c)";
      container.classList.add("critical-risk");
    }
  });

  var tablinks = document.getElementsByClassName("tab-links");
  var tabcontents = document.getElementsByClassName("tab-contents");

  window.opentab = function(tabname) {
    for (let tablink of tablinks) {
      tablink.classList.remove("active-link");
    }
    for (let tabcontent of tabcontents) {
      tabcontent.classList.remove("active-tab");
    }
    event.currentTarget.classList.add("active-link");
    document.getElementById(tabname).classList.add("active-tab");
  }

  window.openmenu = function () {
    document.getElementById("sidemenu").style.right = "0";
  }

  window.closemenu = function () {
    document.getElementById("sidemenu").style.right = "-200px";
  }

document.addEventListener("DOMContentLoaded", function () {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.classList.add('visible');
      }
    });
  }, { threshold: 0.3 });

  const image = document.querySelector('.about-col-1.dev-photo');
  if (image) {
    observer.observe(image);
  }
});
});
