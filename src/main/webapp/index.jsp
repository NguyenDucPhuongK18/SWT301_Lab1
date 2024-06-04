<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Homepage</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="navbar.jsp"%>
        <div class="container-fluid my-4">
            <div class="row">
                <div class="col-4">
                    <div class="card mx-2 my-2" style="width: 18rem;height: 450px">
                        <div class="card-body">
                            <h5 class="card-title">Exciting Breakthrough in Quantum Computing Unveiled</h5>
                            <p class="card-text">Researchers at a leading tech institute have announced a significant advancement in quantum computing, potentially heralding a new era in computational power. The breakthrough promises to revolutionize industries reliant on massive data processing, such as finance, healthcare, and cybersecurity.</p>
                            <a href="#" class="btn btn-primary">Read More</a>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="card" style="width: 18rem;height: 450px">
                        <div class="card-body">
                            <h5 class="card-title">Artificial Intelligence Innovation Takes Center Stage</h5>
                            <p class="card-text">A cutting-edge AI algorithm developed by a team of researchers has demonstrated remarkable capabilities in natural language processing, raising the bar for machine understanding and communication. This breakthrough promises to enhance virtual assistants, streamline customer service, and revolutionize data analysis.</p>
                            <a href="#" class="btn btn-primary">Learn More</a>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="card" style="width: 18rem;height: 450px">
                        <div class="card-body">
                            <h5 class="card-title">Blockchain Technology Disrupts Supply Chain Management</h5>
                            <p class="card-text">A new blockchain-based solution is set to transform supply chain management, offering unprecedented transparency and efficiency. This innovative platform promises to revolutionize logistics, minimize fraud, and ensure the authenticity of products from manufacturing to delivery.</p>
                            <a href="#" class="btn btn-primary">Discover More</a>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="card" style="width: 18rem;height: 450px">
                        <div class="card-body">
                            <h5 class="card-title">Cybersecurity Firm Unveils Advanced Threat Detection System</h5>
                            <p class="card-text">Leading cybersecurity experts have introduced a state-of-the-art threat detection system, leveraging advanced machine learning algorithms to identify and neutralize cyber threats in real-time. This groundbreaking technology promises to bolster digital defenses, safeguard sensitive data, and protect against evolving cyberattacks.</p>
                            <a href="#" class="btn btn-primary">Explore Further</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        <script>
            const moon = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-moon-stars mb-1" viewBox="0 0 16 16">
<path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278zM4.858 1.311A7.269 7.269 0 0 0 1.025 7.71c0 4.02 3.279 7.276 7.319 7.276a7.316 7.316 0 0 0 5.205-2.162c-.337.042-.68.063-1.029.063-4.61 0-8.343-3.714-8.343-8.29 0-1.167.242-2.278.681-3.286z" /><path d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z" />
</svg>`;

            const sun = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-brightness-high mb-1" viewBox="0 0 16 16">
<path d="M8 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z"/>
</svg>`;
            (() => {
                "use strict";

                const getStoredTheme = () => localStorage.getItem("theme");
                const setStoredTheme = (theme) => localStorage.setItem("theme", theme);

                const getPreferredTheme = () => {
                    const storedTheme = getStoredTheme();
                    if (storedTheme) {
                        return storedTheme;
                    }

                    return window.matchMedia("(prefers-color-scheme: dark)").matches
                        ? "dark"
                        : "light";
                };

                const setTheme = (theme) => {
                    if (
                        theme === "auto" &&
                        window.matchMedia("(prefers-color-scheme: dark)").matches
                    ) {
                        document.documentElement.setAttribute("data-bs-theme", "dark");
                    } else {
                        document.documentElement.setAttribute("data-bs-theme", theme);
                    }
                };

                setTheme(getPreferredTheme());

                const showActiveTheme = (theme, focus = false) => {
                    const themeSwitcher = document.querySelector("#bd-theme");

                    if (!themeSwitcher) {
                        return;
                    }

                    const themeSwitcherText = document.querySelector("#bd-theme-text");
                    const activeThemeIcon = document.querySelector(".theme-icon-active use");
                    const btnToActive = document.querySelector(
                        `[data-bs-theme-value="\${theme}"]`
                    );
                    const svgOfActiveBtn = btnToActive
                        .querySelector("svg use")
                        .getAttribute("href");

                    document.querySelectorAll("[data-bs-theme-value]").forEach((element) => {
                        element.classList.remove("active");
                        element.setAttribute("aria-pressed", "false");
                    });

                    btnToActive.classList.add("active");
                    btnToActive.setAttribute("aria-pressed", "true");
                    activeThemeIcon.setAttribute("href", svgOfActiveBtn);
                    const themeSwitcherLabel = `\${themeSwitcherText.textContent} (\${btnToActive.dataset.bsThemeValue})`;
                    themeSwitcher.setAttribute("aria-label", themeSwitcherLabel);

                    if (focus) {
                        themeSwitcher.focus();
                    }
                };

                window
                    .matchMedia("(prefers-color-scheme: dark)")
                    .addEventListener("change", () => {
                        const storedTheme = getStoredTheme();
                        if (storedTheme !== "light" && storedTheme !== "dark") {
                            setTheme(getPreferredTheme());
                        }
                    });

                window.addEventListener("DOMContentLoaded", () => {
                    showActiveTheme(getPreferredTheme());

                    document.querySelectorAll("[data-bs-theme-value]").forEach((toggle) => {
                        toggle.addEventListener("click", () => {
                            const theme = toggle.getAttribute("data-bs-theme-value");
                            setStoredTheme(theme);
                            setTheme(theme);
                            showActiveTheme(theme, true);
                        });
                    });
                });

                document.addEventListener("DOMContentLoaded", () => {
                    let switchButton = document.getElementById("switch");
                    let currentTheme = document.documentElement.getAttribute("data-bs-theme");
                    let switchIcon = document.getElementById("icon");
                    if (currentTheme === "dark") {
                        switchButton.checked = true;
                        switchIcon.innerHTML = moon;
                    } else {
                        switchButton.checked = false;
                        switchIcon.innerHTML = sun;
                    }
                });
            })();

            function switchTheme() {
                let switchButton = document.getElementById("switch").checked;
                let switchIcon = document.getElementById("icon");
                if (switchButton) {
                    document.documentElement.setAttribute("data-bs-theme", "dark");
                    switchIcon.innerHTML = moon;
                } else {
                    document.documentElement.setAttribute("data-bs-theme", "light");
                    switchIcon.innerHTML = sun;
                }
            }
        </script>
    </body>
</html>