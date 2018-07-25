package com.demo.image;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Bas64 图片字符转为图片实例
 *
 * @author RyanYin
 */
public class Base64Image {

    public static void main(String[] args) {

        //图片base64字符串
        String base64Str = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAIBAQEBAQIBAQECAgICAgQDAgICAgUEBAMEBgUGBgYFBgYGBwkIBgcJBwYGCAsICQoKCgoKBggLDAsKDAkKCgr/2wBDAQICAgICAgUDAwUKBwYHCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgr/wAARCAB4AHgDASIAAhEBAxEB/8QAHgAAAQUBAQEBAQAAAAAAAAAABwAFBggJBAMCAQr/xAA6EAABAwIFAwIFAwIEBgMAAAABAgMEBREABgcSIQgxQRMiFDJRYXEJgZEVoRYjQrEkM1KSwdFicvH/xAAbAQACAwEBAQAAAAAAAAAAAAADBAIFBgABB//EADERAAEDAwMBBgQGAwAAAAAAAAEAAgMEESEFEkExBhMiUWGBFHGxwRUjMpGh0ULw8f/aAAwDAQACEQMRAD8AwjXVq/R/VgRpC1sVJpDT7Lo4UAoKAufl5A7W7Y41QYAiqWkOIlN33JBBQTewH/n74UxCaosLYdCrq5Spy39jiWZTyTHrb0BiNHS6oAestCfmAVe1vOBuf3TQUxDE+d+0KadMeV4XxE6s1liM6gNj0lS0bUKPgWvzc8Y046MdPa5k5gZwW22z/VY7bcluM7ZpKU2KUpT4H1uTijnS1pFl3NdRgUJ/eqUicRKZV8rSULNz3Pe2NLNHaE7GhqpqOYrHta2cJI4/9Yxmq1T3yFo6lfQNApO7hBdwrSabziqClT7SUp2gNkW4vxxiaU4nctpxxSiVWufpgXaVVaIISYcldnWlexJPcYKlFVFfbQ5dRJV7gDzinIeAAr99inL4T4aMFLN7cj3Y6pFSZkU9lv09pSfB745a2fTgModQFIeJSb/Uc/8AjDXS0GMlbaXSpCfehSjcWvbHA2NghNiF73T5EfUolx03ChZST2x4Vp0vNll0JAPIVtvbjHbAbSiJ68kpG63t24aqqXHX1GMv2p+a/a2DOdII8r0uAwq8dWWhGWdVNO6jQMxZYaqsZu647SRYi497fBFwfv55xiLrR0Xaj5TzTMpdDyxK9OE5IebekxwEIZC/YkmxNwDb78dsf0HZ5iCXCWww4206t0rINylY44H8YEvVDphlWFozPzC9Q471QSwXnZgQApZAHzfYDwLYlp9dNSS+HoltRo4K6Czuo5X882ZMp5roEBypzGj6apHoktyNllp7Eg+Occ2acyTpNSNSclLQopaSghF0uJA5uRxe/nBN64aa7T87qchhBifFEpdasAvfdQuPqNpwIKdsXEDU2QpbSVCyF9im/O3743sEomga/wA18wqo+5qCwcIj6Uw6esrzG7TIbgbb96mJPpXPfkKvdV/Pb7YWO7RfKaptPlwgw6w82orprrzW1LnhTS/qbG4tb98LAXP2uObojW+Hog1AjtuPJQ8ypVxeyBc/74tH016Zx5eXYecE0pU5PprS0y+g7EGxFzttwL37+MVmpbkQOqafTu4HB72vi9HRjqDQcx0ROlOnz66rXYtHMlmK1FcU2jZy5uDaVFViPANgCT9QLVPiHRhsTSSn9IfSxOLpiAPVTXRbL2RdHpys1ZhzFT6bBU9ucU7ZBcKipSttzc+5X8DFt9KOpTp8zRFTTcn55huqCbBKztTc/fGWmcIrWstRn1MVup1qriYoFtqnOMwohuf8suOlPYd9qD/69KLo7rFleNHcy/XKYJQIcTEgLfUv7XISBb73xTyafDt/MfZ3qr6n1SoItCy7PQLZvL2YWI74kx67GLYb3JW29Y/fg98EHKOuOVKO+INSrza3XVI9BKbhRB7n+bYwpqGsfUnpr8OY2ZMyQ5FlCa5KmBbKCVkj07EG2y1wb+cSLJXXvr1lGoRq7m2oivR2nQEywi7mwrB2mxPY28j98efhTgfA4FMs1t4P50ZAW8er+pbdL07pWaIDlkRq1HEpQBtsdG0k/a5GOegav5TRHcVUZjKVOoKY67+0c3OKvdIXWbpjrto+7kjO1eZUuoQ0ITGdlBLiFX3IVYm9wR3+2Aj1jar510Xq0im0CrLepslpQjuNvBsxXED3D1FcG/B4J+nN8VjKeV8+21rq2kqIWU5fwObjK0RZ6odNoC/QzTXEMgEhJCkhKiAR5PHH184hWpX6imhGQ4iY9NlMSpBUd4eeSAPpcgkEX/GMHM9606w5szZJqlRztVpEf1SX2YDilWR4VuXZPa/jEk0Tzpp7Xqs7VdQc/VlECnL3vR5eXnJ8ZtveEJU+7GcRb3qQm1vmUMXLdPjYzc7JHl91n36nNLJta3bfFzhalxv1YssVXMbtMzXkV9EBcja2/BAWW0/Xk9vP4wec9VPJ2v8A0+1kZDrLUxt2kqUlQFloUE7tpTc24FsZ7ZNy3n/MVPTUdEalpVmFC0B1FPvU6TJcQCLbQ8hYNhwbKPnxh10w/UZzV0f6iHTTWHo9zSzW8wtqTFYo1bakRqgjsSypdgvg/YgXxXSUkdU8iAAkcX+yfbVy0rA6ow3zthZ/9RbdPq2fp+SJslSHxV1oWog+xKFEi3/coYFtUNLTXnaXAeZLUQFth1KDyq1rnBE6ltSctZ21mqmfaFFcpjkitSJKqDNRtfp4beKSy6R7Ss9+CRa/bviGycuU+puSM1UZ1AZlpKmmHDtUh1Zsf25xqqSMxUrWOHAWGrZGz1DnM5UypOeE0/JKKrToshRfUhmaS+LJfTuCXUkWsLhN8LEbh0RVBpzMZ5Cng+ApLKl7Wnwi6iD979iLeMLBBGBwhd5sADkNkNF19W1Vgok3He2LJZN0RpNL0TjV+dp9VmKhUKaqRTa9FnOtFN07wFBJ2qQSOxHbzgIZMgMf4eqdVU20pSAG0pcTcpPH+/bGwnT3pHRIGhOWco5jhGSlOWoyVtO2KSFN3A7fcYq9brn07GbfP6K87P6fFU79/l9VUjp80yE/QahMZWZ9Z5TTjqWGG9ynXS4r3kDvaxH4HN8NuddJdYMy0KsxoVTl0H4ZoLpaSdrkx4EbgpfG3i9gLC9u+LL6c6Q0fp9zg9lDMFSepcFc2TKyrV5DBTDlQ3FFwM+sPa08hW4FCgLjt9ynlunZU1DStTeYqStbbpALMhl0L/Nlm372xQmseyo70NuD91poaSGalEDjYgWOVQXpp6d+ocZogVfOVDqVKojdNebkhqoKkGe6CoodWh1xYK7q52hIsOADzg2TdAsgIybUc0awaZwIMaAyt+PUmkJZkSdovtU0jlN+17+cWdrWRaLTacqJWa7HYbR7kKMltCUnyeVWA/fFZOrTN2Xs5U5nRbSCrorddrEpmMtqmP8ArCMFLFlKUDZQKgLi/wAu7zzhs1UlU8Oc0gckYAURSRadTlrPETwcm/GEP/05v08671iVqsaxVDV2u5Mo9LkqgUxVLioXIfSlRuAV2T7QdpNvmB/GJ51G/pr1PpL6jdKKRm3V3MGo+m+c685CSKpMLS41QsSGFC5QC4SFJsB8pxoF0d6MULQDIdJ0hoN3P6PSUJnyFD3Py3FF15Z4tcrUr6YlfVt0/Reo7QSbpbLlCNVEOtVDK9SWnmDU2FByM6CPl9yQgn/pWr848OpzumNz4Tce3QFTg0KlbRsuwd4LG/r1t9lTvUTowyxQ81LXR9OpLFJ9DaMvPUtKQx7SDvUnl1XzfMbXtxgNUH9OnI+WM+UmuVbNeZ5dJpMr4lvLq2EJS2pTiXFBKVqsU+0XAR4xoFoH1/8ATPqrREZG1+ridP8AUCiI+CrtMzeyYTL0psbFusSVD0nUKI3JuvdZXnBGzFH6f89ZeepkvOuUpiXW/wDhJ7FciqUnyLKCzxe1/tfABNWQt2tJz88/NMSR0lUWulZcj2+qqdl7RnponVw5vypW3aHMC1z26dTEuqWl9QsW0oBAQDxdI45N7jjAB/Ug0T166hZel2RK3kpujUCr5/TS8urdbAklTrClPLWoC5AQ0VWFgL4u/og90R6M1+rZk116jMl06VDlEN01+vR1BfA5SAorc57JQL/nEkpqkdcHUtlnXGlZWnUzSrSqmz3cqSarAciKrtYlNBj4hph0Bfoss7gFqA3KcuOOMCp3TxyNqJcHPzyuq/h5mfDtyMX/AKWFfXz0jU7pc12TpjRpz0uLNorMyFICClJcUpSVpAVckbk2v3574B9JnVSl/EQWnPa0LrYcubgd7fTtjUb9bvKtCyvqZpjrPUIKX44nS6PUlqFxY2cb4/8Aid5H4xmBm+Kmi55nMxJnrNtPkpfSbbkkXI/hWNVptQ+ppW7x1H8rFa5Qw01S4sxnp6EXClOVnn67SFtVJl5VNSkeo/HlpWpsE39oNinmwsTyTa474WOfL1fyzMadgVWK3DiPOK9CUwzctFKb3Iv7hfk358ixwsONu3AJCqo2s25TVlamvLZgwEr2pny2w7uHf3DG0+ltZaXkyhOsOlxKqXHAKBcWCALfyP4xi5l94KeiI+KWpxtSVNqIvs/FrY046BtWKjnHTFik1SUkvQUJaS53uhNwOL3vzzjOdoYyWNcOCVqezUrWyuZixAVpG47nwPw7FTcQh1QPpvNodR35uhYIN/JPP3txjim9K+leclqqVUydlb1lkFUmPlVhlwqP1LQSSb45qPIkIlht926doUVKF7c+MEDKuYflcQQsAcFSBwb28YzcckjeSB+y2jmxvuHAH2Qhz/0WZMq8gqrLT7FLQyltcKMyUByxH3P+2O3LGiOnemeX6fCyPlCHT2qfObcTwA48sKFlKPc2BwY67UFzkKV8QVulN9p8n6YHuqsTNdMy+muZfy7ImLiErkRWQN/a/FzzibXyz/5Ej3K9jp4RZwaB8gEbdFJlPjQ3E1Ge38fIWlxRuSSg38YPLGVKXKyu5IqC0BLYCVOqVYjcOCPp4xkjlPqq13yLnuVVs51+hvxFsj0aJAW65Nh7blPqL/5fI4KeCL9/BPOlP6geoGv9DqGTMoJFMqSWdjM6rsOqjIct7StKeVAAHsf4w2yF7DdwQ3SRy4jdY+SsXqR046fZozK5XawFw504ASpzUZtbc23G5aVAgqtwSe+GeB+n1pKFf1JjJuQqgVEq9OqZFhrCuL8lKAT+98dHTOxr9Mon9N1+dpEtuOkJYrNIS4GJm75ShLhKkgd+T4wWKMqpZcluQXXkKjk7WXQo837cjAAHQuyTa/miyWcNu4E/IH+eqHWn3RHoxpfnyoanQMoZch1Kawyy21RKGzGYY2XO9KQm6VG5BIxPKjV0UqAul/FexI4ClE/Yefvj1zLJkOlPqSSoFzatzaRc+LfXENr0pfroiuk71EpU6pVgDiLgZL9UtYA2JWcv640yI7ojQqO4j1JBzcl9obCSNrLpNh99xH8Yyz1IpsOFKjVNhBCZ9LaWSo8lYJQo28fKL40s/WorDczOdJy8XXXYrbRkbk/IlVrAXHn7+cZy5syy9OdEmQ2YyUXG1F1BLZJPtBubEnGs0dr46cX6f8WI7Qls1W8j0/hRyLO/prbcd9khXzoueCTbkW+nfCx1RcsNVwellye5JktHaiMtog//ALhYuu9AOFmg13C+aYG0R2GQPcHApSwSnj6X+mLR9JXUHK0+orkOGf8ANUSiOUcWuOfz5GKpNhYjBiQ+pSFWtx838fTEryrWnKQ+wEqcALlklK7bQb8/3wjWQCdm0lWFDM+nmBHK1E0n6qKbmyl0MJa2TphCZbTirgqAF7fTknvfFjcp1F5+KzUCfa4mwaR2Kjfj8+cZddOuo/8AQ0yswBsByIprYlCwraparHaD54xa6pdVz0XS5tNGqLqZTSXN7uwJISgkbx91XsD+eMZaqonNfZvuttR6kHR73qwOsXUfpZobTHHs0VdiTWgw4qHRI6wt99SUFXKQfakW5JPj9sUD1569tadfokmjrzi7len/ABCGl0+lqKVAkElK3E3V4FrEc8c3wLJMzU/XrVSSKS9KLriyiVUDdaGmVXvuItz5wSst6U0TTCoyJEeujNdVfUfh2aNTlvJB4FlhIKbi3BJGHIYKekYL5clnO1HVZQIxZn1TXpb/AIfy7EkP1FiozGo7ra5Rf3JMski6iSCpVr3/AAMEvTbqGjUPNdRpdbqq4jiFx0U5tlhxpC0FJSUi5AIIVbnDnknSnW2e2/W6dovXlKfKX/Skw2rLQO4ss3A/Ax05i6buo3WWU2p/Q1TK0BKQVlptRAN09vIIH8YMJgARbqrKPszXgBwd04Uhh9YWrmk7tMrEDPFQiU6Q9JQqBJd3tsrSR6bdjcWNz57ducWm6V/1NqPqMyin6i05tse1Jl01/wBUKUdvu9MjixPPPGKm5a6NOrqLR5tErGlLM6nOyQqRFkVdtDrSgeHGwvjd28247HtiF5g6a9UdJc2DOdJy/MgL3rdco0h4FJSALhKkK4Htv9+R5ws8x1DdjhYpeag1CgG++FsfIrlNlUpFTgzQ9DcYU42sc38Xt47jAb1JzWJtJdRHqYjyFKS/Gd3gAp7bVD7mwt98Bfor6uXM76b1vLlWXJZfiKDlOjv/ADhSyR6Sb97FPH/nAO1V6i69S5r0tFYSr0HlEpWo2UtshSgBfg9/txhRtLKHFllOSrj2iQIFfqI52reoeapFSnkNpbUYyYjgICQ0DwSTxfk/ewxV7Nb7Ko8ymPw2iREQ+lXrbXEp4Fkm9iBye3jFh+rOu0zN+Z2CmWgyagyhb7IRuQpwthVz9OFHx3tisOrlQq2WoLdCEVswZTW1DvoAOpKbEDd9OTjQaeHCNrSsnqUge9z1GcuZih5OqArdLbUqY04VR5aXx9DYkEWJGFhuypFptTqiIlZlqRHKkl19lncWk+Tt8/fCxbPDAclUdnvyDZKG+YaA2ASlJ9pI4T9cONIceUo1KSUEKulF/lFseNXoSYDTbrM34iM82gtus2AJKQbEcng3H8Y8pUd19huLDdt7rvJJ9o4tiDgCbFGaSGhTvIeaqrT1PR4ToSH3kOpPI3qQVKCBb/7H+BgqS8zVdyOzSKlIU0uQEh1HqkraQDewFux3HvgV5DnUmgyUVCBUkyH4rQDaS3xu7bEX7fcm/F8dtXzgG660qbVVl+S82qS5vvtSnnbf8/TCsg3HdZNxzGNourl6QxsnZfyYtaqYy225/muyW0hQttsNw8m/98GrSrNGWFQFzKHHVG9FKVNBtASFH/2e+KNxdQ3mdO462KyuS2+76j8ZpewobCrFKh/t97YnvT/qzm55wQqfW/SO0NNRd17g83WTflI54twMU09EZHbwc3WnotVMA2cDpZWnzP1cZ8y1Tw1lzUhll6S4PhhLgpWlSb8pva/BGI9lH9TjP2XnC7qDIdDDbiS9Ng0ZAbCSUgKHuJ4uSfxirGtWYn8tTn1xfXb+HkKMEqKiopWdp89yVE8WGIDl/M0iTQmHKhGWtbc703UqUTvStCSgp7ng9/GDxUDHRbsldL2hrxJYG3sttNGtRaJqlRmc5QqixVETGwpp5t4KFrke8Dz2P2xDOqd3JuXZsWvTXWWm3UraV7wRYdyARiqHR31R5RyXAk5aXOfpdVjNFIiJQUNqaUd6lBNj55vfgYa+p/qVOYazEbarapcFLb3opKQTuVwCORuF/wDUALd/GK9lGTKdyfm1YSUoPJ6r4yZrBT6dWMxuUhpcdhdQLhTDQFLjrTv9JSTftuAV/OAXqZqDGnVKfUoFV9VhTjbkcqSdylLHuJseB7VDgeTiI07Orceo5iZgvvpkpipejttqtyCVKQTexSefvewwPyJspuMlqoXRIdDK0pd2raVt3DsOBdRHJ84u4qcAb3LKSVdxtuuzVHPkmfLjy1yHkyYuwArUAtQSng9h44x7ahU3/Fuijdbq7YcksqDsVYHuJACNigOebjn64j1WSzLmKVXXi6lCCkLU0VLJSLWvfkXv/GO92sQEZLNKemSUOvOtMsWHuUlIO37AE2J+4GGDgAt/30SJfu3bj1Q5Vl+qZfRGk1Knra+JZNihF1IPgEcfXCxKqxmejNZdWxUwlye2Sdziy4lYI8jsFXAt4wsGY6R4vtUXRwRnBvdQGHOTIU61GbS2lTxUhH/SCQbfjHXUX2oylOholOwC6flWrt/GG2nNRG/TdQHBuTdQ+3PnEhq8aO3l1FcQtKkIKGS0OwcKl3P/AGgfucFcAH4SzQTFdcCa5NhwnJDLgLvpAAgWFr9h+MfkaovNQ2oz7pWHAVF4dwCRc8+bXw0qkoW6Em6QHLoQTxh0j2rUxCmY4KktpZ5XybecSLfD0URucbKYQJ7xpUemuPqDJJdbQTyUXHHHftf84KWjVXiUNTVSlyfSQ6lTi7EhQKjt7/jAmYS16UaQ6taVtslvaLWBvz/bHq7m+rUt5hqBJS8fVAItwgHgX+vNv3wExCQENTAe2MguyrD6j1dzONYeQHGlhNLKo58ocuCOPwP74HLUdNKZTRnkl51qVvZT6gspKkgWHm/H7Xw103VwLrKqo7LUXlPNoWAgEKAIBH4tf+MLOeYaVUqg5UG57zIiquFMNC5A3cD+AD9icLNgfCdowEx37S3de9k95Zz2iBOkQ5U5l1FN3uRSUhK7k29Er/1WuTze9recfuYM+N5thJpM+eqNLjKBivuElN+/plKbbe9h4vbAyydWkCqLQuUS9OStp1txkEMq3exQJNiSDzjsvV4FReD0tCvXeUoONkE77jnjxxg5gs4E5KF8Q97SF8pm11uSYoj+g+16pVIUfctaTcDtYi1+MPGT61HqMh1iaGmHpDaVyFKSbFq6QSAP9QIBv247Yac11GGzPUqJOcfQ6PWinuUqUgXvz3vcY8KQyqNXy9LdW0lLSQVk+5IUObgDt4/fDBaNl0vfxgKWVnZ/h92UJCHXYjrjaFpWmxRYkqKbXBKSfybYjjtTaTJECNFLqynew6QFLbBFgpQJAAucP7TMPM9ZkZSptRbMNxv/ACJ7jNwlYIve3J89zif5U6aHomUncxOqD3xQDhfdZIK0kcG4PbjtgDAf02VtR0M9ZbZ+nkoD5IypEzlmJNArdYj07ev3SZO9R783QgWJPjnCwYqLpW0/XzSqT6ZfluIMh0Ng25tYG1xhYm9j3O8G4D2T8OkuY2xaD+6r5KoVQgBTMho7E8AJ/wBI+4x6u1RlmC/R5LR+FfKSLeFDsR+/9r4WFid7vsqSeFkbSBx/a4ZCUsltlK21gJt6iRwDjtoz8uDMIKUkJSSolP27i3nCwscSbJO1iFIqdKSYbCJBKkpKilIVbcFcG5PnnHqkQYrqZAaW0ltY9Nt8gXF+/bnCwsDaTcKY5TdGppg1JDVQSFKS6PVQ2okqQTcW7e6x7/2w8ZkMSYy85HaejMrbVa6wQFKWsA2+lgB+ThYWJvKGLBR3LCHKLndKg2H0odspS2gpJNh4xJFS2mVqlqHoJ/zApv0ha+64wsLEJpHNIspDlMKGn3HENtL2uIX6qE7UkFIFgP7Ylj0zLtaSlbNQfaqKUJ2oFglZ4vcfTCwsSF3xFxU6drXPsVN8oZMmZdorEplSVypbwaDrYtdTqvcAPs3f98WQgZmkU/KjGV36fsaEdKWUNosmyeOfr9cLCwSMZuvo1BEyCnOzGF85DyZQY61VaFGHxDz3/MWj5VX7fjCwsLDQiY4XT0Vi1f/Z";

        //保存路径
        String outPath = "/data1/home/whaty/workspace/idea-local/Demo/out/huzi.jpg";
        boolean flag = generateImage(base64Str, outPath);
        System.out.println("flag = " + flag);
//        String imgPath = "/data1/home/whaty/workspace/idea-local/Demo/out/yinxu1.jpg";
//        String str = getImageStr(imgPath);
//        System.out.println("str = " + str);

    }

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    private final static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
