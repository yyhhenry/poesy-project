package cn.d619.poesy.article;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/api/article/add")
    public MsgDTO addArticle(@RequestBody ArticlePO articlePO) {
        articleService.addArticle(articlePO);
        return new MsgDTO(true, "添加成功");
    }

    @GetMapping("/api/article/search")
    public MsgDTO searchArticle(@RequestParam("title") String title) {
        articleService.searchArticle(title);
        return new MsgDTO(true, "搜索成功");
    }
    
}
