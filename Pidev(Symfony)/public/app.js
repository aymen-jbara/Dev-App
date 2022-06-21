function main() {

 const ip = fetch('https://api.ipify.org?format=json')
        .then(resultat => resultat.json() )
        .then(json => {
            const ip = json.ip;
            console.log(ip);

            fetch('http://freegeoip.net/json/' + ip)
                .then(json => {
                    const ville = json.city;
                    console.log(ville);
                })
        })
    console.log(ip);

}