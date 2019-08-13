<%@tag description="Manager Navigation" pageEncoding="UTF-8"%>
<%@attribute name="activeIndex" required="true" type="java.lang.Integer" %>
<div class="d-flex">
    <ul class="nav navbar-nav">
        <li class="nav-item">
            <a class="nav-link ${activeIndex eq 0 ? 'active' : ''}" href="manager/options">
                Опции
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${activeIndex eq 1 ? 'active' : ''}" href="manager/tariffs">
                Тарифы
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${activeIndex eq 2 ? 'active' : ''}" href="manager/clients">
                Клиенты
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${activeIndex eq 3 ? 'active' : ''}" href="manager/contracts">
                Контракты
            </a>
        </li>
    </ul>
</div>
<div class="d-flex">
    <form class="form-inline mr-2">
        <input class="form-control mr-2" type="search" placeholder="Поиск" aria-label="Search">
        <button class="btn btn-outline-light" type="submit">Найти</button>
    </form>
    <a class="nav-link text-light ml-2 small" href="#">Выйти</a>
</div>