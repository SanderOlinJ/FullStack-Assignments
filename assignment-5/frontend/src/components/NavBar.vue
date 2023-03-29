<template>
    <nav class="navbar">
        <div class="navbar__container">
        <div class="navbar-icons">
            <router-link to="/" class="icon-link" exact-active-class="active">
            <h1>Log in</h1>
            </router-link>

            <router-link to="/signup" class="icon-link" exact-active-class="active">
            <h1>Sign up</h1>
            </router-link>
        </div>
        </div>
    </nav>
</template>


<style scoped>

.navbar {
    position: fixed;
    background-color: rgb(61, 61, 61);
    top: 0;
    left: 0;
    right: 0;
    height: 75px;
    z-index: 9999;
    width: 100%;
}

.navbar__container {
    display: flex;
    justify-content: space-between;
    justify-content: center;
    align-items: center;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}

.navbar-icons{
    display: flex;
    height: 73px;
    width: 300px;
    justify-content: center;
    justify-content: space-between;
    align-items: center;
    padding: 0;
}


h1{
    color: white;
    text-decoration: none;
    font-size: 17px;
    font-family: 'Lato', sans-serif;
}

a{
    display: flex;
    justify-content: center;
    align-items: center;
    text-decoration: none;
}

.icon-link{
    transition: transform 0.3s;
    padding: 10px;
}

.icon-link:hover {
    transform: translateY(-2px);
    border-bottom: 4px solid white;
}

</style>
