# Boilerplate BiBiFi Repo

## Testing locally before making a push

1. Install docker on your system

2. Build docker image

```sh
docker build -t bibifi . --network=host
```

3. Run docker image with local `build` folder source mounted as `bibifi_build`
        
```sh
docker run --rm --network=host -i -t -v ${PWD}/build:/bibifi_build bibifi bash
```

4. Try building sample project

```sh
cd bibifi_build/ && make
```

5. Run `logappend` and `logread`

```sh
./logappend
./logread
```

6. Clean generated files

```sh
make clean
```
