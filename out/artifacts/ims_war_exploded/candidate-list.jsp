<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Candidate</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            width: 100%;
        }

        img {
            width: 100px;
            height: 200px;
        }

        #candidateImage, #viewCandidateImage, #updatedCandidateImage {
            height: 100px;
            width: 100px;
        }

        td {
            text-align: center;
        }

        span {
            font-weight: bold;
        }

        #icon {
            margin-top: 4px;
        }

        input[type=text], input[type=date], input[type=file], select {
            border: 1px solid gray;
            border-radius: 10px;
            display: inline-block;
            margin: 10px 10px 10px 0;
            padding: 10px;
            box-sizing: border-box;
            width: 100%;
        }

        label, input[type=file] {
            width: 90%;
        }

        a:link, a:visited {
            text-decoration: none;
        }

        .focused {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<c:set var="currentUri" value="${pageContext.request.requestURI}"/>
<%@ include file="navbar.jsp" %>
<c:if test="${'Administrator'.equals(sessionScope.user.memberRole.memberRole)
|| 'HR Manager'.equals(sessionScope.user.memberRole.memberRole)}">
    <div style="margin: 10px 10px 10px 0;text-align: end">
        <a href="candidate/export" class="btn btn-info">Export</a>
        <button type="button" class="btn btn-info"
                data-bs-toggle="modal" data-bs-target="#addCandidate">New candidate
        </button>
    </div>
</c:if>
<div class="container-fluid mt-3 mb-5">
    <div class="row">
        <c:forEach var="candidate" items="${requestScope.candidateList}">
            <div class="col-3">
                <div class="card mx-2 my-4" style="width: 18rem;height:450px;">
                    <c:set var="avatarPath"
                           value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/${candidate.candidateImage}"/>
                    <img src="${avatarPath}" class="card-img-top"
                         alt="${candidate.candidateFullName}'s image">
                    <div class="card-body">
                        <h5 class="card-title">${candidate.candidateFullName}</h5>
                        <p class="card-text">Address: ${candidate.candidateAddress}</p>
                        <p class="card-text">Email: ${candidate.candidateEmail}</p>
                        <p class="card-text">Phone number: ${candidate.candidatePhoneNumber}</p>
                        <a href="#" data-bs-toggle="modal" data-bs-target="#viewCandidate${candidate.candidateId}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-eye-fill" viewBox="0 0 16 16">
                                <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0"></path>
                                <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8m8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7"></path>
                            </svg>
                        </a>
                        <c:if test="${'Administrator'.equals(sessionScope.user.memberRole.memberRole)
                                    || 'HR Manager'.equals(sessionScope.user.memberRole.memberRole)}">
                            <a href="#" data-bs-toggle="modal"
                               data-bs-target="#deleteCandidate${candidate.candidateId}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-trash" viewBox="0 0 16 16">
                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"></path>
                                    <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"></path>
                                </svg>
                            </a>
                            <a href="#" data-bs-toggle="modal"
                               data-bs-target="#updateCandidate${candidate.candidateId}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-pencil-square" viewBox="0 0 16 16">
                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                                    <path fill-rule="evenodd"
                                          d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"></path>
                                </svg>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
            <%--        View modal--%>
            <div class="modal fade" id="viewCandidate${candidate.candidateId}" data-bs-backdrop="static"
                 data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="viewCandidateLabel${candidate.candidateId}" aria-hidden="true">
                <div class="modal-dialog modal-fullscreen">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="viewCandidateLabel">View candidate's information</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body"
                             style="display:grid;
                                grid-template-columns: repeat(2, 50%)">
                            <div>
                                <label> <span>Full name:</span>
                                    <input type="text" value="${candidate.candidateFullName}" disabled>
                                </label>
                            </div>
                            <div>
                                <label> <span>Address:</span>
                                    <input type="text" value="${candidate.candidateAddress}" disabled>
                                </label>
                            </div>
                            <div>
                                <label> <span>Phone number:</span>
                                    <input type="text" value="${candidate.candidatePhoneNumber}" disabled>
                                </label>
                            </div>
                            <div>
                                <label> <span>Email:</span>
                                    <input type="text" value="${candidate.candidateEmail}" disabled>
                                </label>
                            </div>
                            <div>
                                <label> <span>Date of birth:</span>
                                    <input type="date" value="${candidate.candidateDob}" disabled>
                                </label>
                            </div>
                            <div>
                                <label> <span>Status:</span>
                                    <input type="text" value="${candidate.candidateStatus.getCandidateStatus()}"
                                           disabled>
                                </label>
                            </div>
                            <div>
                                <span>Image:</span>
                                <img id="viewCandidateImage"
                                     src="unknown.jpg"
                                     alt="">
                            </div>
                            <div>
                                <span>CV attachment:</span>
                                <a href="${pageContext.request.contextPath}/${candidate.candidateCvAttachment}"
                                   class="btn btn-primary" download>Download here</a>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
            <%--        End update modal--%>
            <%--        Update modal--%>
            <div class="modal fade" id="updateCandidate${candidate.candidateId}" data-bs-backdrop="static"
                 data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="updateCandidateLabel${candidate.candidateId}" aria-hidden="true">
                <div class="modal-dialog modal-fullscreen">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="updateCandidateLabel">Update candidate's information</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="${pageContext.request.contextPath}/candidate/update"
                              method="post">
                            <div class="modal-body"
                                 style="display:grid;
                                grid-template-columns: repeat(2, 50%)">
                                <input type="hidden" value="${candidate.candidateId}" name="candidateId">
                                <div>
                                    <label> <span>Full name:</span>
                                        <input type="text" name="candidateFullName" placeholder="Full name..."
                                               value="${candidate.candidateFullName}">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Address:</span>
                                        <input type="text" name="candidateAddress" placeholder="Address..."
                                               value="${candidate.candidateAddress}">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Phone number:</span>
                                        <input type="text" name="candidatePhoneNumber" placeholder="Phone number..."
                                               value="${candidate.candidatePhoneNumber}">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Email:</span>
                                        <input type="text" name="candidateEmail" placeholder="Email..."
                                               value="${candidate.candidateEmail}">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Date of birth:</span>
                                        <input type="date" name="candidateDob" value="${candidate.candidateDob}">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Status:</span>
                                        <select name="candidateStatus">
                                            <c:forEach var="status" items="${requestScope.candidateStatus}">
                                                <option value="${status}" ${status == candidate.candidateStatus ? 'selected' : ''}>${status.getCandidateStatus()}</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </div>
                                <div>
                                    <span>Image:</span> <input id="updateImageInput" type="file" accept="image/*"
                                                               onchange="updateCandidateImage(event)">
                                    <img id="updatedCandidateImage"
                                         src="unknown.jpg"
                                         alt="">
                                    <input type="hidden" id="updatedCandidateImg" name="candidateImage" value="">
                                </div>
                                <div>
                                    <span>CV attachment:</span> <input type="file" id="updatedCvInput"
                                                                       accept=".pdf,.doc,.docx,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation">
                                    <input type="hidden" id="updatedCandidateCv" name="candidateCvAttachment"
                                           value="">
                                    <a href="${pageContext.request.contextPath}/${candidate.candidateCvAttachment}"
                                       class="btn btn-primary" download>Download current CV</a>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%--        End update modal--%>
            <%--        Add modal--%>
            <div class="modal fade" id="addCandidate" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="addCandidateLabel" aria-hidden="true">
                <div class="modal-dialog modal-fullscreen">
                    <div class="modal-content">
                        <div class="modal-header" style="margin-right:20px">
                            <h1 class="modal-title fs-5" id="addCandidateLabel">Add candidate</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="${pageContext.request.contextPath}/candidate/add" method="post"
                              enctype="multipart/form-data">
                            <div class="modal-body" style="
                            display: grid;
                            grid-template-columns: repeat(2, 50%)">
                                <div>
                                    <label> <span>Full name:</span>
                                        <input type="text" name="candidateFullName" placeholder="Full name..."
                                               id="first-name">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Address:</span>
                                        <input type="text" name="candidateAddress" placeholder="Address...">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Phone number:</span>
                                        <input type="text" name="candidatePhoneNumber" placeholder="Phone number...">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Email:</span>
                                        <input type="text" name="candidateEmail" placeholder="Email...">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Date of birth:</span>
                                        <input type="date" name="candidateDob">
                                    </label>
                                </div>
                                <div>
                                    <label> <span>Status:</span>
                                        <select name="candidateStatus">
                                            <c:forEach var="status" items="${requestScope.candidateStatus}">
                                                <option value="${status}">${status.getCandidateStatus()}</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </div>
                                <div>
                                    <span>Image:</span>
                                    <input id="addImage" type="file" accept="image/*"
                                           onchange="addCandidateImage(event)" name="inputCImage">
                                    <img id="candidateImage" src="${pageContext.request.contextPath}/unknown.jpg"
                                         alt="">
                                    <input type="hidden" id="addedFilename" name="candidateImage" value="">
                                </div>
                                <div>
                                    <span>CV attachment:</span> <input type="file" id="addCv"
                                                                       onchange="addCandidateCv(event)"
                                                                       accept=".pdf,.doc,.docx,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation">
                                    <input type="hidden" id="candidateCv" name="candidateCvAttachment"
                                           value="">
                                </div>
                            </div>
                            <div class="modal-footer" style="margin-right: 20px">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%--            End add modal--%>
            <%--        Delete modal--%>
            <div class="modal fade" id="deleteCandidate${candidate.candidateId}" data-bs-backdrop="static"
                 data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="deleteCandidateLabel${candidate.candidateId}" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="deleteCandidateLabel${candidate.candidateId}">
                                Confirmation</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body" style="color: red">
                            Are you sure that you want to delete this candidate? This action is irreversible!
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                            <a href="${pageContext.request.contextPath}/candidate/delete?candidateId=${candidate.candidateId}"
                               class="btn btn-primary">Yes</a>
                        </div>
                    </div>
                </div>
            </div>
            <%--        End delete modal--%>
        </c:forEach>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
<script>
    const myModal = document.getElementById('myModal')
    const myInput = document.getElementById('myInput')

    myModal.addEventListener('shown.bs.modal', () => {
        myInput.focus()
    })
</script>
<script>
    function addCandidateImage(event) {
        const file = event.target.files[0];
        const reader = new FileReader();

        reader.onload = function(e) {
            document.getElementById('candidateImage').src = e.target.result;
        };

        reader.readAsDataURL(file);

        document.getElementById('addedFilename').value = file.name;
    }

    function addCandidateCv(event) {
        const file = event.target.files[0];

        document.getElementById('candidateCv').value = file.name;
    }
</script>
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
