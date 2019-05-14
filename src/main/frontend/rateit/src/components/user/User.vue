<template>
     <div class="col-sm-12 com-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"> Dane użytkownika: </h3>
            </div>
            <div class="panel-body">
                <div class="pull-left">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Szczegóły:</h3>
                        </div>
                        <div class="panel panel-body">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Imię</li>
                                <li class="list-group-item"><router-link to="/rates" activeClass="active" tag="li"><a>Moje oceny</a></router-link></li>
                                <li class="list-group-item"><router-link to="/owned" activeClass="active" tag="li"><a>Posiadane albumy</a></router-link></li>
                                <li class="list-group-item"><router-link to="/reviews" activeClass="active" tag="li"><a>Moje recenzje</a></router-link></li>
                                <li class="list-group-item"><router-link to="/wishlist" activeClass="active" tag="li"><a>Wishlist</a></router-link></li>
                            </ul>
                        </div>
                    </div>
                </div>
                
                <div class="class panel panel-default">
                <div class="panel-heading">Ostatnia aktywność</div>
                <div class="panel panel-body">
                    <div class="pull-center">
                        <div class="col-sm-6 com-md-3">
                            <p> {{ record }}</p>
                        </div>
                    </div>
                </div>
                
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    data() {
        return {
            record: []
        }
    }
   
}
</script>
