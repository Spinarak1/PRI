<template>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <router-link to="/" class="navbar-brand">Rate it!</router-link>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <router-link to="/user" activeClass="active" tag="li"><a>User</a></router-link>
                    <router-link to="/records" activeClass="active" tag="li"><a>Records</a></router-link>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a
                            href="#"
                            class="dropdown-toggle"
                            data-toggle="dropdown"
                            role="button"
                            aria-haspopup="true"
                            aria-expanded="false"><span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">x</a></li>
                            <li><a href="#">x</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>
