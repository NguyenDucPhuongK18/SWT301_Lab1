<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary" style="height: 10vh">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">IMS</a>
        <div class="collapse navbar-collapse d-flex" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link ${fn:contains(currentUri, '/candidate')? 'active focused' : ''}" aria-current="page" href="candidate">Candidate</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${fn:contains(currentUri, '/job')? 'active focused' : ''}" aria-current="page" href="job">Job</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${fn:contains(currentUri, '/interview')? 'active focused' : ''}" aria-current="page" href="interview">Interview</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${fn:contains(currentUri, '/member')? 'active focused' : ''}" aria-current="page" href="member">Member</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto me-3" style="margin-bottom: 3px;">
                <li class="nav-item">
                    <div class="form-check form-switch mt-2">
                        <input class="form-check-input" type="checkbox" role="switch" id="switch" onchange="switchTheme()">
                        <label class="form-check-label" id="icon"></label>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"></path>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"></path>
                        </svg>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="profile">My Profile</a></li>
                        <li><a href="logout" class="dropdown-item">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>